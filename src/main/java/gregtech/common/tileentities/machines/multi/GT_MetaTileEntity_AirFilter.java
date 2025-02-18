package gregtech.common.tileentities.machines.multi;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;

import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.common.GT_Pollution;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;

import static gregtech.api.enums.GT_Values.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by danie_000 on 03.10.2016.
 */
public class GT_MetaTileEntity_AirFilter extends GT_MetaTileEntity_MultiBlockBase {
    protected int mPollutionReduction=0;
    protected int baseEff = 2500*4;
    protected boolean hasPollution=false;
    static final GT_Recipe tRecipe= new GT_Recipe(
            new ItemStack[]{ItemList.AdsorptionFilter.get(1L, new Object())},
            new ItemStack[]{ItemList.AdsorptionFilterDirty.get(1L,new Object())},
            null, null, null, null, 1, 1, 0);

    public GT_MetaTileEntity_AirFilter(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_AirFilter(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_AirFilter(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "Controller Block for the Air Filter",
                "Size(WxHxD): 3x4x3 (Hollow), Controller (Front middle bottom)",
                "8x Air Filter Vent Casing (Two middle Layers, corners)",
                "1-8x Muffler Hatch (Two middle Layers, sides)",
                "1x Input Bus (Any bottom layer casing)",
                "1x Output Bus (Any bottom layer casing)",
                "1x Energy Hatch (Any bottom layer casing)",
                "1x Maintenance Hatch (Any bottom layer casing)",
                "Air Filter Turbine Casings for the rest",
                "Can accept Adsorption filters, Turbine (in controller)",
                "Machine tier*2 = Maximal useful muffler tier",
                "Features Hysteresis control (tm)"
        };
    }

    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return new ITexture[]{Textures.BlockIcons.casingTexturePages[0][16], TextureFactory.of(aActive ? Textures.BlockIcons.OVERLAY_FRONT_DIESEL_ENGINE_ACTIVE : Textures.BlockIcons.OVERLAY_FRONT_DIESEL_ENGINE)};
        }
        return new ITexture[]{Textures.BlockIcons.casingTexturePages[0][16]};
    }
    
    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_MultiMachine(aPlayerInventory, aBaseMetaTileEntity, getLocalName(), "ElectricAirFilter.png");
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
    }

    @Override
    public boolean isFacingValid(byte aFacing) {
        return aFacing > 1;
    }

    @Override
    public boolean checkRecipe(ItemStack aStack){
        mEfficiencyIncrease = 10000;
        mEfficiency = 10000 - (getIdealStatus() - getRepairStatus()) * 1000;
        mPollutionReduction = 0;
        mMaxProgresstime = 200;
        mEUt=-32;
        if(!hasPollution) {
            return true;
        }

        try{
            if(aStack.getItem() instanceof GT_MetaGenerated_Tool_01 &&
                    ((GT_MetaGenerated_Tool) aStack.getItem()).getToolStats(aStack).getSpeedMultiplier()>0 &&
                    ((GT_MetaGenerated_Tool) aStack.getItem()).getPrimaryMaterial(aStack).mToolSpeed>0 ) {
                baseEff = (GT_Utility.safeInt((long) ((50.0F
                        + 10.0F * ((GT_MetaGenerated_Tool) aStack.getItem()).getToolCombatDamage(aStack)) * 100))) * 4;
            } else {
                baseEff = 2500*4;
            }
        }catch (Exception e){
            baseEff = 2500*4;
        }

        long tVoltage = getMaxInputVoltage();
        byte tTier = (byte) max(1, GT_Utility.getTier(tVoltage));

        calculateOverclockedNessMulti(GT_Utility.safeInt(tVoltage-(tVoltage>>2)), 200, 1, tVoltage);
        //In case recipe is too OP for that machine
        if (mEUt == Integer.MAX_VALUE - 1) {
            return false;
        }
        if (mEUt > 0) {
            mEUt = -mEUt;
        }

        ArrayList<ItemStack> tInputList = getStoredInputs();
        int tInputList_sS=tInputList.size();
        for (int i = 0; i < tInputList_sS - 1; i++) {
            for (int j = i + 1; j < tInputList_sS; j++) {
                if (GT_Utility.areStacksEqual(tInputList.get(i), tInputList.get(j))) {
                    if (tInputList.get(i).stackSize >= tInputList.get(j).stackSize) {
                        tInputList.remove(j--); tInputList_sS=tInputList.size();
                    } else {
                        tInputList.remove(i--); tInputList_sS=tInputList.size();
                        break;
                    }
                }
            }
        }

        for (GT_MetaTileEntity_Hatch_Muffler tHatch : mMufflerHatches) {
            if (isValidMetaTileEntity(tHatch)) {
                mPollutionReduction+= min(tTier*2,tHatch.mTier)*300;//reduction per muffler tier
            }
        }

        ItemStack[] tInputs = Arrays.copyOfRange(tInputList.toArray(new ItemStack[0]), 0, 2);
        if (!tInputList.isEmpty()) {
            if (tRecipe.isRecipeInputEqual(true, null, tInputs)) {
                mPollutionReduction*=2;//bonus for filter
                mOutputItems = new ItemStack[]{tRecipe.getOutput(0)};
                updateSlots();
            }
        }

        mPollutionReduction = GT_Utility.safeInt((long) mPollutionReduction * baseEff) / 10000;
        mPollutionReduction = GT_Utility.safeInt((long) mPollutionReduction * mEfficiency / 10000);

        Chunk tChunk = getBaseMetaTileEntity().getWorld().getChunkFromBlockCoords(getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getZCoord());
        int xChunk = tChunk.xPosition;
        int zChunk = tChunk.zPosition;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                tChunk = getBaseMetaTileEntity().getWorld().getChunkFromChunkCoords(xChunk + i, zChunk + j);
                GT_Pollution.addPollution(tChunk, -mPollutionReduction);
            }
        }

        ItemStack stackTool = mInventory[1];
        if (stackTool != null && stackTool.getItem() instanceof GT_MetaGenerated_Tool_01) {
            GT_MetaGenerated_Tool tool = (GT_MetaGenerated_Tool_01) stackTool.getItem();
            if (tool.getToolStats(stackTool).getSpeedMultiplier() > 0 && GT_MetaGenerated_Tool_01.getPrimaryMaterial(stackTool).mToolSpeed > 0) {
                tool.doDamage(stackTool, 50L);
            }
        }
        return true;
    }
    
    @Override
    protected void calculateOverclockedNessMulti(int aEUt, int aDuration, int mAmperage, long maxInputVoltage) {
        byte mTier=(byte) max(0,GT_Utility.getTier(maxInputVoltage));
        mMaxProgresstime=aDuration;
        if(mTier==0){
            //Long time calculation
            mEUt=aEUt/4;
        }else{
            //Long EUt calculation
            long xEUt=aEUt;
            //Isnt too low EUt check?
            long tempEUt = max(xEUt, V[1]);

            while (tempEUt <= V[mTier -1] * mAmperage) {
                tempEUt *= 4;//this actually controls overclocking
                xEUt *= 4;//this is effect of overclocking
            }
            if(xEUt>Integer.MAX_VALUE-1){
                mEUt = Integer.MAX_VALUE-1;
                mMaxProgresstime = Integer.MAX_VALUE-1;
            }else{
                mEUt = (int)xEUt;
                mEUt = mEUt == 0 ? 1 : mEUt;
            }
        }
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        int xDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(aBaseMetaTileEntity.getBackFacing()).offsetZ;
        int one = 1;
        int two = 2;

        //air check and top casing check
        if (!aBaseMetaTileEntity.getAirOffset(xDir, one, zDir) || !aBaseMetaTileEntity.getAirOffset(xDir, two, zDir)) {//check air inside
            return false;
        }
        for(int i=-one;i<two;i++) {
            for (int j = -one; j < two; j++) {
                if (!aBaseMetaTileEntity.getAirOffset(xDir+i, 4, zDir+j) || !aBaseMetaTileEntity.getAirOffset(xDir+i, 5, zDir+j)) {//check air at on top of top layer
                    return false;
                }
                if (aBaseMetaTileEntity.getBlockOffset(xDir+i, 3, zDir+j) != GregTech_API.sBlockCasingsNH) {
                    return false;//top casing
                }
                if (aBaseMetaTileEntity.getMetaIDOffset(xDir+i, 3, zDir+j) != 0) {
                    return false;//top casing
                }

            }
        }
        if (!aBaseMetaTileEntity.getAirOffset(two+xDir, one, zDir) || !aBaseMetaTileEntity.getAirOffset(two+xDir, two, zDir)) {
            return false;
        }
        if (!aBaseMetaTileEntity.getAirOffset(xDir-two, one, zDir) || !aBaseMetaTileEntity.getAirOffset(xDir-two, two, zDir)) {
            return false;
        }
        if (!aBaseMetaTileEntity.getAirOffset(xDir, one, two+zDir) || !aBaseMetaTileEntity.getAirOffset(xDir, two, two+zDir)) {
            return false;
        }
        if (!aBaseMetaTileEntity.getAirOffset(xDir, one, zDir-two) || !aBaseMetaTileEntity.getAirOffset(xDir, two, zDir-two)) {
            return false;
        }


        if (!aBaseMetaTileEntity.getAirOffset(two+xDir, one, one+zDir) || !aBaseMetaTileEntity.getAirOffset(two+xDir, two, one+zDir)) {
            return false;
        }
        if (!aBaseMetaTileEntity.getAirOffset(two+xDir, one, zDir-one) || !aBaseMetaTileEntity.getAirOffset(two+xDir, two, zDir-one)) {
            return false;
        }

        if (!aBaseMetaTileEntity.getAirOffset(xDir-two, one, one+zDir) || !aBaseMetaTileEntity.getAirOffset(xDir-two, two, one+zDir)) {
            return false;
        }
        if (!aBaseMetaTileEntity.getAirOffset(xDir-two, one, zDir-one) || !aBaseMetaTileEntity.getAirOffset(xDir-two, two, zDir-one)) {
            return false;
        }

        if (!aBaseMetaTileEntity.getAirOffset(one+xDir, one, two+zDir) || !aBaseMetaTileEntity.getAirOffset(one+xDir, two, two+zDir)) {
            return false;
        }
        if (!aBaseMetaTileEntity.getAirOffset(xDir-one, one, two+zDir) || !aBaseMetaTileEntity.getAirOffset(xDir-one, two, two+zDir)) {
            return false;
        }

        if (!aBaseMetaTileEntity.getAirOffset(one+xDir, one, zDir-two) || !aBaseMetaTileEntity.getAirOffset(one+xDir, two, zDir-two)) {
            return false;
        }
        if (!aBaseMetaTileEntity.getAirOffset(xDir-one, one, zDir-two) || !aBaseMetaTileEntity.getAirOffset(xDir-one, two, zDir-two)) {
            return false;
        }

        //air check and top casing check done

        //muffler check
        mMufflerHatches.clear();
        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(one+xDir, one, zDir), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(one+xDir, one, zDir)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(one+xDir, one, zDir)!= 0) {
                return false;
            }
        }
        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(one+xDir, two, zDir), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(one+xDir, two, zDir)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(one+xDir, two, zDir)!= 0) {
                return false;
            }
        }

        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir-one, one, zDir), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(xDir-one, one, zDir)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(xDir-one, one, zDir)!= 0) {
                return false;
            }
        }
        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir-one, two, zDir), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(xDir-one, two, zDir)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(xDir-one, two, zDir)!= 0) {
                return false;
            }
        }

        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir, one, one+zDir), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(xDir, one, one+zDir)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(xDir, one, one+zDir)!= 0) {
                return false;
            }
        }
        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir, two, one+zDir), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(xDir, two, one+zDir)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(xDir, two, one+zDir)!= 0) {
                return false;
            }
        }

        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir, one, zDir-one), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(xDir, one, zDir-one)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(xDir, one, zDir-one)!= 0) {
                return false;
            }
        }
        if(!addMufflerToMachineList(aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir, two, zDir-one), 160)){
            if(aBaseMetaTileEntity.getBlockOffset(xDir, two, zDir-one)!= GregTech_API.sBlockCasingsNH) {
                return false;
            }
            if(aBaseMetaTileEntity.getMetaIDOffset(xDir, two, zDir-one)!= 0) {
                return false;
            }
        }
        if(mMufflerHatches.isEmpty()) {
            return false;
        }
        //muffler check done
        //pipe casing check
        if(aBaseMetaTileEntity.getBlockOffset(one+xDir, one, one+zDir)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(one+xDir, one, one+zDir)!= 1) {
            return false;
        }
        if(aBaseMetaTileEntity.getBlockOffset(one+xDir, two, one+zDir)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(one+xDir, two, one+zDir)!= 1) {
            return false;
        }

        if(aBaseMetaTileEntity.getBlockOffset(xDir-one, one, one+zDir)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(xDir-one, one, one+zDir)!= 1) {
            return false;
        }
        if(aBaseMetaTileEntity.getBlockOffset(xDir-one, two, one+zDir)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(xDir-one, two, one+zDir)!= 1) {
            return false;
        }

        if(aBaseMetaTileEntity.getBlockOffset(one+xDir, one, zDir-one)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(one+xDir, one, zDir-one)!= 1) {
            return false;
        }
        if(aBaseMetaTileEntity.getBlockOffset(one+xDir, two, zDir-one)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(one+xDir, two, zDir-one)!= 1) {
            return false;
        }

        if(aBaseMetaTileEntity.getBlockOffset(xDir-one, one, zDir-one)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(xDir-one, one, zDir-one)!= 1) {
            return false;
        }
        if(aBaseMetaTileEntity.getBlockOffset(xDir-one, two, zDir-one)!= GregTech_API.sBlockCasingsNH) {
            return false;
        }
        if(aBaseMetaTileEntity.getMetaIDOffset(xDir-one, two, zDir-one)!= 1) {
            return false;
        }
        //pipe casing check done
        //bottom casing
        for (int i = -one; i < two; i++) {
            for (int j = -one; j < two; j++) {
                if (xDir + i != 0 || zDir + j != 0) {//sneak exclusion of the controller block
                    IGregTechTileEntity tTileEntity = aBaseMetaTileEntity.getIGregTechTileEntityOffset(xDir + i, 0, zDir + j);
                    if (!addMaintenanceToMachineList(tTileEntity, 160) && !addInputToMachineList(tTileEntity, 160) && !addOutputToMachineList(tTileEntity, 160) && !addEnergyInputToMachineList(tTileEntity, 160)) {
                        if (aBaseMetaTileEntity.getBlockOffset(xDir + i, 0, zDir + j) != GregTech_API.sBlockCasingsNH) {
                            return false;
                        }
                        if (aBaseMetaTileEntity.getMetaIDOffset(xDir + i, 0, zDir + j) != 0) {
                            return false;
                        }
                    }
                }
            }
        }
        //bottom casing done
        return true;
    }

    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 10000;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        if (aBaseMetaTileEntity.isServerSide()) {
            if (mMachine && aTick % 200L == 0L) {
                int pollution = 0;
                Chunk tChunk = getBaseMetaTileEntity().getWorld().getChunkFromBlockCoords(getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getZCoord());
                int xChunk = tChunk.xPosition;
                int zChunk = tChunk.zPosition;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        tChunk = getBaseMetaTileEntity().getWorld().getChunkFromChunkCoords(xChunk + i, zChunk + j);
                        pollution += GT_Pollution.getPollution(tChunk);
                    }
                }
                //check for pollution
                hasPollution = pollution >= 10000 || (hasPollution && pollution >= 1000);
            }
        } else if (aTick % 200L == 0L) {
            //refresh casing on state change
            int Xstart = aBaseMetaTileEntity.getXCoord();
            int Ystart = aBaseMetaTileEntity.getYCoord();
            int Zstart = aBaseMetaTileEntity.getZCoord();
            final int RANGE = 32;
            try {
                aBaseMetaTileEntity.getWorld().markBlockRangeForRenderUpdate(
                        Xstart - RANGE,
                        Ystart,
                        Zstart - RANGE,
                        Xstart + RANGE,
                        Ystart,
                        Zstart + RANGE);
            } catch (Exception e) {
                GT_Log.out.print(e);
            }
        }
        super.onPostTick(aBaseMetaTileEntity, aTick);
    }

    @Override
    public int getPollutionPerTick(ItemStack aStack) {
        return 0;//set to zero since it will directly affect pollution in chunk
    }

    @Override
    public int getDamageToComponent(ItemStack aStack) {
        try{
            if(aStack.getItem() instanceof GT_MetaGenerated_Tool_01 &&
                    ((GT_MetaGenerated_Tool) aStack.getItem()).getToolStats(aStack).getSpeedMultiplier()>0 &&
                    ((GT_MetaGenerated_Tool) aStack.getItem()).getPrimaryMaterial(aStack).mToolSpeed>0 ) {
                return 10;
            }
        }catch (Exception e){/**/}
        return 0;
    }

    public int getAmountOfOutputs() {
        return 1;
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return false;
    }

    @Override
    public String[] addInfoData() {
        final ArrayList<String> ll = new ArrayList<>();
        ll.add("Pollution reduction: "+ EnumChatFormatting.GREEN + mPollutionReduction + EnumChatFormatting.RESET+" gibbl/t (for Chunk)");
        final String[] a = new String[ll.size()];
        return ll.toArray(a);
    }
}
