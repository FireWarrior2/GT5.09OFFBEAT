package gregtech.common.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.items.GT_MetaGenerated_Item_X32;
import gregtech.api.objects.GT_MultiTexture;

import gregtech.api.render.TextureFactory;
import gregtech.common.covers.GT_Cover_FluidRegulator;

public class GT_MetaGenerated_Item_03
        extends GT_MetaGenerated_Item_X32 {
    public static GT_MetaGenerated_Item_03 INSTANCE;

    public GT_MetaGenerated_Item_03() {
        super("metaitem.03", new OrePrefixes[]{OrePrefixes.crateGtDust, OrePrefixes.crateGtIngot, OrePrefixes.crateGtGem, OrePrefixes.crateGtPlate});
        INSTANCE = this;
        int tLastID = 0;
        Object[] o = new Object[0];
        
        /**
         * circuit boards tier 1-7:
         * coated circuit board / wood plate + resin
         * Plastic Circuit Board / Plastic + Copper Foil + Sulfuric Acid
         * phenolic circuit board /carton+glue+chemical bath
         * epoxy circuit board /epoxy plate + copper foil + sulfuric acid
         * fiberglass circuit board (simple + multilayer) / glass + plastic + electrum foil + sulfurci acid
         * wetware lifesupport board / fiberglass CB + teflon + 
         */
//        ItemList.Circuit_Board_Coated.set(addItem(tLastID = 1, "Coated Circuit Board", "A basic Board"));
//        ItemList.Circuit_Board_Phenolic.set(addItem(tLastID = 2, "Phenolic Circuit Board", "A good Board"));
//        ItemList.Circuit_Board_Epoxy.set(addItem(tLastID = 3, "Epoxy Circuit Board", "An advanced Board"));
//        ItemList.Circuit_Board_Fiberglass.set(addItem(tLastID = 4, "Fiberglass Circuit Board", "An advanced Board"));
//        ItemList.Circuit_Board_Multifiberglass.set(addItem(tLastID = 5, "Multilayer Fiberglass Circuit Board", "A elite Board"));
        ItemList.Circuit_Board_Wetware.set(addItem(tLastID = 6, "Wetware Lifesupport Circuit Board", "The Board that keeps life"));
        ItemList.Circuit_Board_Plastic.set(addItem(tLastID = 7, "Polymer Circuit Board", "A Good Board"));
        ItemList.Circuit_Board_Bio.set(addItem(tLastID = 8, "Bio Circuit Board", "Bio genetic mutated Board"));
        ItemList.Circuit_Board_Crystal.set(addItem(tLastID = 9, "Xeno Circuit Board", "Xeno Board on Oriharukon base"));
        
        /**
         * electronic components:
         * vacuum tube (glass tube + red alloy cables)
         * basic electronic circuits normal+smd
         * coils
         * diodes normal+smd
         * transistors normal+smd
         * capacitors normal+smd
         * Glass Fibers
         */
//        ItemList.Circuit_Parts_Resistor.set(addItem(tLastID = 10, "Resistor", "Basic Electronic Component")); //wiring mv
        ItemList.Circuit_Parts_ResistorSMD.set(addItem(tLastID = 11, "SMD Resistor", "Electronic Component"));
        ItemList.Circuit_Parts_Glass_Tube.set(addItem(tLastID = 12, "Glass Tube", ""));
//        ItemList.Circuit_Parts_Vacuum_Tube.set(addItem(tLastID = 13, "Vacuum Tube", "Basic Electronic Component")); //Circuit_Primitive
        ItemList.Circuit_Parts_Coil.set(addItem(tLastID = 14, "Small Coil", "Basic Electronic Component"));
//        ItemList.Circuit_Parts_Diode.set(addItem(tLastID = 15, "Diode", "Basic Electronic Component"));
        ItemList.Circuit_Parts_DiodeSMD.set(addItem(tLastID = 16, "SMD Diode", "Electronic Component"));
//        ItemList.Circuit_Parts_Transistor.set(addItem(tLastID = 17, "Transistor", "Basic Electronic Component")); //wiring hv
        ItemList.Circuit_Parts_TransistorSMD.set(addItem(tLastID = 18, "SMD Transistor", "Electronic Component"));
//        ItemList.Circuit_Parts_Capacitor.set(addItem(tLastID = 19, "Capacitor", "Electronic Component")); //wiring ev
        ItemList.Circuit_Parts_CapacitorSMD.set(addItem(tLastID = 20, "SMD Capacitor", "Electronic Component"));
        ItemList.Circuit_Parts_GlassFiber.set(addItem(tLastID = 21, "Glass Fiber", Materials.BorosilicateGlass.mChemicalFormula));
        ItemList.Circuit_Parts_PetriDish.set(addItem(tLastID = 22, "Petri Dish", "For cultivating cells"));
        ItemList.Circuit_Parts_Reinforced_Glass_Tube.set(addItem(tLastID = 23, "Reinforced Glass Tube", ""));
        ItemList.Circuit_Parts_ResistorASMD.set(addItem(tLastID = 24, "Advanced SMD Resistor", "Advanced Electronic Component"));
        ItemList.Circuit_Parts_DiodeASMD.set(addItem(tLastID = 25, "Advanced SMD Diode", "Advanced Electronic Component"));
        ItemList.Circuit_Parts_TransistorASMD.set(addItem(tLastID = 26, "Advanced SMD Transistor", "Advanced Electronic Component"));
        ItemList.Circuit_Parts_CapacitorASMD.set(addItem(tLastID = 27, "Advanced SMD Capacitor", "Advanced Electronic Component"));
        ItemList.Circuit_Parts_InductorSMD.set(addItem(tLastID = 28, "SMD Inductor", "Electronic Component"));
        ItemList.Circuit_Parts_InductorASMD.set(addItem(tLastID = 29, "Advanced SMD Inductor", "Advanced Electronic Component"));
        ItemList.Circuit_Parts_ResistorXSMD.set(addItem(tLastID = 131, "Xeno SMD Resistor", "Extreme Electronic Component"));
        ItemList.Circuit_Parts_DiodeXSMD.set(addItem(tLastID = 132, "Xeno SMD Diode", "Extreme Electronic Component"));
        ItemList.Circuit_Parts_TransistorXSMD.set(addItem(tLastID = 133, "Xeno SMD Transistor", "Extreme Electronic Component"));
        ItemList.Circuit_Parts_CapacitorXSMD.set(addItem(tLastID = 134, "Xeno SMD Capacitor", "Extreme Electronic Component"));
        ItemList.Circuit_Parts_InductorXSMD.set(addItem(tLastID = 135, "Xeno SMD Inductor", "Extreme Electronic Component"));


        /**
         * ICs
         * Lenses made from perfect crystals first instead of plates
         * Monocrystalline silicon ingot (normal+glowstone+naquadah) EBF, normal silicon no EBF need anymore
         * wafer(normal+glowstone+naquadah) cut mono silicon ingot in cutting machine
         * 
         * Integrated Logic Circuit(8bit DIP)
         * RAM
         * NAND Memory
         * NOR Memory
         * CPU (4 sizes)
         * SoCs(2 sizes, high tier cheap low tech component)
         * Power IC/High Power IC 
         * 
         * nanotube interconnected circuit (H-IC + nanotubes)
         * 
         * quantum chips
         */
        ItemList.Circuit_Silicon_Ingot.set(addItem(tLastID = 30, "Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Ingot2.set(addItem(tLastID = 31, "Glowstone doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Ingot3.set(addItem(tLastID = 32, "Naquadah doped Monocrystalline Silicon Boule", "Raw Circuit"));

        ItemList.Circuit_Silicon_Wafer.set(addItem(tLastID = 33, "Wafer", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer2.set(addItem(tLastID = 34, "Glowstone doped Wafer", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer3.set(addItem(tLastID = 35, "Naquadah doped Wafer", "Raw Circuit"));
             
        ItemList.Circuit_Wafer_ILC.set(addItem(tLastID = 36, "Integrated Logic Circuit (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_ILC.set(addItem(tLastID = 37, "Integrated Logic Circuit", "Integrated Circuit"));
        
        ItemList.Circuit_Wafer_Ram.set(addItem(tLastID = 38, "Random Access Memory Chip (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_Ram.set(addItem(tLastID = 39, "Random Access Memory Chip", "Integrated Circuit"));
        
        ItemList.Circuit_Wafer_NAND.set(addItem(tLastID = 40, "NAND Memory Chip (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_NAND.set(addItem(tLastID = 41, "NAND Memory Chip", "Integrated Circuit"));

        ItemList.Circuit_Wafer_NOR.set(addItem(tLastID = 42, "NOR Memory Chip (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_NOR.set(addItem(tLastID = 43, "NOR Memory Chip", "Integrated Circuit"));

        ItemList.Circuit_Wafer_CPU.set(addItem(tLastID = 44, "Central Processing Unit (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_CPU.set(addItem(tLastID = 45, "Central Processing Unit", "Integrated Circuit"));

        ItemList.Circuit_Wafer_SoC.set(addItem(tLastID = 46, "SoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC.set(addItem(tLastID = 47, "SoC", "System on a Chip"));

        ItemList.Circuit_Wafer_SoC2.set(addItem(tLastID = 48, "ASoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC2.set(addItem(tLastID = 49, "ASoC", "Advanced System on a Chip"));

        ItemList.Circuit_Wafer_PIC.set(addItem(tLastID = 50, "PIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_PIC.set(addItem(tLastID = 51, "Power IC", "Power Circuit"));

        ItemList.Circuit_Wafer_HPIC.set(addItem(tLastID = 52, "HPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_HPIC.set(addItem(tLastID = 53, "High Power IC", "High Power Circuit"));

        ItemList.Circuit_Wafer_NanoCPU.set(addItem(tLastID = 54, "NanoCPU Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_NanoCPU.set(addItem(tLastID = 55, "Nanocomponent Central Processing Unit", "Power Circuit"));

        ItemList.Circuit_Wafer_QuantumCPU.set(addItem(tLastID = 56, "QBit Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_QuantumCPU.set(addItem(tLastID = 57, "QBit Processing Unit", "Quantum CPU"));
        
        ItemList.Circuit_Wafer_UHPIC.set(addItem(tLastID = 58, "UHPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_UHPIC.set(addItem(tLastID = 59, "Ultra High Power IC", "Ultra High Power Circuit"));

        ItemList.Circuit_Wafer_Simple_SoC.set(addItem(tLastID = 110, "Simple SoC Wafer", "Raw Primitive Circuit"));
        ItemList.Circuit_Chip_Simple_SoC.set(addItem(tLastID = 111, "Simple SoC", "Simple System on a Chip"));
        
        ItemList.Circuit_Wafer_SoC3.set(addItem(tLastID = 60, "HASoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC3.set(addItem(tLastID = 61, "HASoC", "High Advanced System on a Chip"));
        
        ItemList.Circuit_Wafer_SoC4.set(addItem(tLastID = 62, "UHASoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC4.set(addItem(tLastID = 63, "UHASoC", "Ultra High Advanced System on a Chip"));
        
        ItemList.Circuit_Wafer_ULPIC.set(addItem(tLastID = 112, "ULPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_ULPIC.set(addItem(tLastID = 113, "Ultra Low Power IC", "Ultra Low Power Circuit"));
        ItemList.Circuit_Wafer_LPIC.set(addItem(tLastID =114, "LPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_LPIC.set(addItem(tLastID = 115, "Low Power IC", "Low Power Circuit"));
        
        ItemList.Circuit_Chip_RPico.set(addItem(tLastID = 121, "Raw Pico Wafer", ""));
        ItemList.Circuit_Chip_Pico.set(addItem(tLastID = 122, "Pico Wafer", ""));
        
        ItemList.Circuit_Wafer_NPIC.set(addItem(tLastID = 160, "NPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_NPIC.set(addItem(tLastID = 161, "Nano Power IC", "Nano Power Circuit"));
        ItemList.Circuit_Wafer_PPIC.set(addItem(tLastID = 162, "PPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_PPIC.set(addItem(tLastID = 163, "Piko Power IC", "Piko Power Circuit"));
        ItemList.Circuit_Wafer_QPIC.set(addItem(tLastID = 164, "QPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_QPIC.set(addItem(tLastID = 165, "Quantum Power IC", "Quantum Power Circuit"));
        ItemList.Circuit_Wafer_FPIC.set(addItem(tLastID = 166, "FPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_FPIC.set(addItem(tLastID = 167, "Femto Power IC", "Femto Power Circuit"));
        
        ItemList.Circuit_Silicon_Ingot4.set(addItem(tLastID = 64, "Enderium doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer4.set(addItem(tLastID = 65, "Enderium doped Wafer", "Raw Circuit"));
        
        ItemList.Circuit_Silicon_Ingot5.set(addItem(tLastID = 66, "Naquadria doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer5.set(addItem(tLastID = 67, "Naquadria doped Wafer", "Raw Circuit"));
        
		ItemList.Circuit_Silicon_Ingot6.set(addItem(tLastID = 108, "Mysterious Crystal doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer6.set(addItem(tLastID = 109, "Mysterious Crystal doped Wafer", "Raw Circuit"));
        
        ItemList.Circuit_Silicon_Ingot7.set(addItem(tLastID = 150, "Europium doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer7.set(addItem(tLastID = 151, "Europium doped Wafer", "Raw Circuit"));
        
        ItemList.Circuit_Silicon_Ingot8.set(addItem(tLastID = 152, "Americium doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer8.set(addItem(tLastID = 153, "Americium doped Wafer", "Raw Circuit"));
        
        ItemList.Circuit_Silicon_Ingot9.set(addItem(tLastID = 129, "Vibrant Alloy doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer9.set(addItem(tLastID = 130, "Vibrant Alloy doped Wafer", "Raw Circuit"));
		
		ItemList.Circuit_Silicon_Ingot10.set(addItem(tLastID = 154, "Neutronium doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer10.set(addItem(tLastID = 155, "Neutronium doped Wafer", "Raw Circuit"));
        
        /**
         * Engraved Crystal Chip
         * Engraved Lapotron Chip
         * Crystal CPU
         * SoCrystal
         * stem cells (disassemble eggs)
         */
        ItemList.Circuit_Chip_CrystalSoC2.set(addItem(tLastID = 68, "Raw Advanced Crystal Chip", "Raw Advanced Crystal Processor"));
        ItemList.Circuit_Parts_RawCrystalChip.set(addItem(tLastID = 69, "Raw Crystal Chip", "Raw Crystal Processor"));
        ItemList.Circuit_Chip_CrystalCPU.set(addItem(tLastID = 70, "Crystal Processing Unit", "Crystal CPU")); //Crystal chip elite part
        ItemList.Circuit_Chip_CrystalSoC.set(addItem(tLastID = 71, "Crystal SoC", "Crystal System on a Chip"));
        ItemList.Circuit_Chip_NeuroCPU.set(addItem(tLastID = 72, "Neuro Processing Unit", "Neuro CPU"));
        ItemList.Circuit_Chip_Stemcell.set(addItem(tLastID = 73, "Stemcells", "Raw Intiligence"));
        ItemList.Circuit_Parts_RawCrystalParts.set(addItem(tLastID = 74, "Raw Crystal Chip Parts", "Raw Crystal Processor Parts"));
        ItemList.Circuit_Chip_Biocell.set(addItem(tLastID = 76, "Biocells", "Mutated Raw Intiligence"));
        ItemList.Circuit_Chip_BioCPU.set(addItem(tLastID = 77, "Bio Processing Unit", "Bio CPU"));
        ItemList.Circuit_Chip_BarnardaCPU.set(addItem(tLastID = 81, "Barnarda Processing Unit", "Barnarda CPU"));
        ItemList.Circuit_Chip_Barnardacell.set(addItem(tLastID = 136, "Barnarda cells", "Mutated High Intiligence"));
        ItemList.Circuit_Chip_SGCPU.set(addItem(tLastID = 137, "Chevron Processing Unit", "StarGate CPU"));
        ItemList.Circuit_Parts_SGController.set(addItem(tLastID = 138, "Engraved StarGate Controller Crystal Chip", "Needed for Cosmic Chip"));


        ItemList.Circuit_Parts_RawMCrystalChip.set(addItem(tLastID = 116, "Raw Mysterious Crystal Chip", "Raw Mysterious Crystal Processor"));
        ItemList.Circuit_Chip_MCrystalCPU.set(addItem(tLastID = 117, "Mysterious Crystal Processing Unit", "Mysterious Crystal CPU")); //Crystal chip elite part
        ItemList.Circuit_Parts_RawMCrystalParts.set(addItem(tLastID = 118, "Raw Mysterious Crystal Chip Parts", "Raw Mysterious Crystal Processor Parts"));
        ItemList.Circuit_Parts_MCrystal_Chip_Elite.set(addItem(tLastID = 127, "Engraved Mysterious Crystal Chip", "Needed for Circuits"));
        ItemList.Circuit_Parts_MECrystal_Chip_Elite.set(addItem(tLastID = 128, "Engraved Mysterious Energy Crystal Chip", "Needed for Orbs"));
        
        //Nand Chip
        ItemList.NandChip.set(addItem(tLastID = 75, "Nand Chip", "A very simple Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Primitive), SubTag.NO_UNIFICATION}));

        //Vacuum Tube				Item01
        //Basic Circuit				IC2
        //Good Circuit				Item01
        
        //Integrated Logic Circuit  Item01 
        ItemList.Circuit_Integrated_Good.set(addItem(tLastID = 79, "Good Integrated Circuit", "A Good Circuit (MV)",  new Object[]{OrePrefixes.circuit.get(Materials.Good), SubTag.NO_UNIFICATION}));
        //Good Integrated Circuit   Item01
        //Advanced Circuit			IC2
        
        ItemList.Circuit_Microprocessor.set(addItem(tLastID = 78, "Microprocessor", "A Basic Circuit (LV)",  new Object[]{OrePrefixes.circuit.get(Materials.Basic), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Processor.set(addItem(tLastID = 80, "Integrated Processor", "A Good Circuit (MV)",  new Object[]{OrePrefixes.circuit.get(Materials.Good), SubTag.NO_UNIFICATION}));
//        ItemList.Circuit_Computer.set(addItem(tLastID = 81, "Processor Assembly", "Advanced Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Advanced), SubTag.NO_UNIFICATION}));
        //Workstation				Item01 Datacircuit
        //Mainframe					Item01 DataProcessor
        
        ItemList.Circuit_Nanoprocessor.set(addItem(tLastID = 82, "Nanoprocessor", "An Advanced Circuit (HV)",  new Object[]{OrePrefixes.circuit.get(Materials.Advanced), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Nanocomputer.set(addItem(tLastID = 83, "Nanoprocessor Assembly", "An Extreme Circuit (EV)",  new Object[]{OrePrefixes.circuit.get(Materials.Data), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Elitenanocomputer.set(addItem(tLastID = 84, "Elite Nanocomputer", "An Elite Circuit (IV)",  new Object[]{OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION}));
        //Nanoprocessor Mainframe  	Item01 Energy Flow Circuit
        
        ItemList.Circuit_Quantumprocessor.set(addItem(tLastID = 85, "Quantumprocessor", "An Extreme Circuit (EV)",  new Object[]{OrePrefixes.circuit.get(Materials.Data), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Quantumcomputer.set(addItem(tLastID = 86, "Quantumprocessor Assembly", "An Elite Circuit (IV)",  new Object[]{OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Masterquantumcomputer.set(addItem(tLastID = 87, "Master Quantumcomputer", "A Master Circuit (LuV)",  new Object[]{OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Quantummainframe.set(addItem(tLastID = 88, "Quantumprocessor Mainframe", "An Ultimate Circuit (ZPM)",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));
        
        ItemList.Circuit_Crystalprocessor.set(addItem(tLastID = 89, "Crystalprocessor", "An Elite Circuit (IV)",  new Object[]{OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Crystalcomputer.set(addItem(tLastID = 96, "Crystalprocessor Assembly", "A Master Circuit (LuV)",  new Object[]{OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Ultimatecrystalcomputer.set(addItem(tLastID = 90, "Ultimate Crystalcomputer", "An Ultimate Circuit (ZPM)",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Crystalmainframe.set(addItem(tLastID = 91, "Crystalprocessor Mainframe", "A Super Circuit (UV)",  new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION}));
        
        ItemList.Circuit_Neuroprocessor.set(addItem(tLastID = 92, "Wetwareprocessor", "A Master Circuit (LuV)",  new Object[]{OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Wetwarecomputer.set(addItem(tLastID = 93, "Wetwareprocessor Assembly", "An Ultimate Circuit (ZPM)",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Wetwaresupercomputer.set(addItem(tLastID = 94, "Wetware Supercomputer", "A Super Circuit (UV)",  new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Wetwaremainframe.set(addItem(tLastID = 95, "Wetware Mainframe", "An Infinite Circuit (UHV)",  new Object[]{OrePrefixes.circuit.get(Materials.Infinite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Ultimate.set(ItemList.Circuit_Ultimatecrystalcomputer.get(1L));//maybe should be removed
        ItemList.Circuit_Biowarecomputer.set(addItem(tLastID = 98, "Biowareprocessor Assembly", "A Super Circuit (UV)",  new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Biowaresupercomputer.set(addItem(tLastID = 99, "Bioware Supercomputer", "An Infinite Circuit (UHV)",  new Object[]{OrePrefixes.circuit.get(Materials.Infinite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Bioprocessor.set(addItem(tLastID = 97, "Bioprocessor", "An Ultimate Circuit (ZPM)",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));
         
        ItemList.Circuit_Board_Coated_Basic.set(addItem(tLastID = 100, "Circuit Board", "A basic Circuit Board"));
        ItemList.Circuit_Board_Phenolic_Good.set(addItem(tLastID = 101, "Good Circuit Board", "A good Circuit Board"));
        ItemList.Circuit_Board_Epoxy_Advanced.set(addItem(tLastID = 102, "Advanced Circuit Board", "An advanced Circuit Board"));
        ItemList.Circuit_Board_Fiberglass_Advanced.set(addItem(tLastID = 103, "More Advanced Circuit Board", "A more advanced Circuit Board"));
        ItemList.Circuit_Board_Multifiberglass_Elite.set(addItem(tLastID = 104, "Elite Circuit Board", "An elite Circuit Board"));
        ItemList.Circuit_Board_Wetware_Extreme.set(addItem(tLastID = 105, "Extreme Wetware Lifesupport Circuit Board", "The Board that keeps life"));
        ItemList.Circuit_Board_Plastic_Advanced.set(addItem(tLastID = 106, "Plastic Circuit Board", "A good Board"));
        ItemList.Circuit_Board_Bio_Ultra.set(addItem(tLastID = 107, "Ultra Bio Mutated Circuit Board", "Bio genetic mutated Board"));
        ItemList.Circuit_Board_Crystal_Extreme.set(addItem(tLastID = 119, "Super Xeno Circuit Board", "Xeno mutated Board"));
        ItemList.Circuit_Biomainframe.set(addItem(tLastID = 120, "Bio Mainframe", "A Bio Circuit (UEV)",  new Object[]{OrePrefixes.circuit.get(Materials.Bio), SubTag.NO_UNIFICATION}));
        
        ItemList.Circuit_Piko.set(addItem(tLastID = 123, "Xeno Mainframe", "A Piko Circuit (UMV)",  new Object[]{OrePrefixes.circuit.get(Materials.Piko), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Quantum.set(addItem(tLastID = 124, "Cosmic Mainframe", "A Quantum Circuit (UXV)",  new Object[]{OrePrefixes.circuit.get(Materials.Quantum), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Nano.set(addItem(tLastID = 125, "Naqua Mainframe", "A Nano Circuit (UIV)",  new Object[]{OrePrefixes.circuit.get(Materials.Nano), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_HighEnergyFlow.set(addItem(tLastID = 126, "High Energy Flow Circuit", "Energy Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Energy), SubTag.NO_UNIFICATION}));
        
        
        ItemList.EnergyCrystal_LV.set(addItem(tLastID = 200, "Small Energium Crystal (T1)", "Reusable"));
	    setElectricStats(32000 + tLastID, 400000L, GT_Values.V[1], 1L, -3L, true);	    
		
	    ItemList.EnergyCrystal_MV.set(addItem(tLastID = 201, "Medium Energium Crystal (T2)", "Reusable"));
	    setElectricStats(32000 + tLastID, 1600000L, GT_Values.V[2], 2L, -3L, true);	    
		
	    ItemList.EnergyCrystal_HV.set(addItem(tLastID = 202, "Large Energium Crystal (T3)", "Reusable"));
	    setElectricStats(32000 + tLastID, 6400000L, GT_Values.V[3], 3L, -3L, true);	    
		
	    ItemList.EnergyCrystal_EV.set(addItem(tLastID = 203, "Huge Energium Crystal (T4)", "Reusable"));
	    setElectricStats(32000 + tLastID, 25600000L, GT_Values.V[4], 4L, -3L, true);	    
		
	    ItemList.EnergyCrystal_IV.set(addItem(tLastID = 204, "Energium Orb (T5)", "Reusable"));
	    setElectricStats(32000 + tLastID, 102400000L, GT_Values.V[5], 5L, -3L, true);
	    
	    ItemList.MysteriousCrystal.set(addItem(tLastID = 205, "Mysterious Energy Crystal", "Reusable"));
	    setElectricStats(32000 + tLastID, 5000000000L, GT_Values.V[8], 8L, -3L, true);
	    
	    ItemList.MysteriousCrystalOrb.set(addItem(tLastID = 206, "Mysterious Energy Crystal Orb", "Reusable"));
	    setElectricStats(32000 + tLastID, 50000000000L, GT_Values.V[9], 9L, -3L, true);
	    
	    ItemList.MysteriousCrystalModule.set(addItem(tLastID = 207, "Mysterious Energy Crystal Module", "Reusable"));
	    setElectricStats(32000 + tLastID, 500000000000L, GT_Values.V[10], 10L, -3L, true);

        ItemList.EnergyCrystal.set(addItem(tLastID = 208, "Energy Crystal", "Reusable"));
        setElectricStats(32000 + tLastID, 5000000L, GT_Values.V[3], 3L, -3L, true);

        ItemList.LapotronCrystal.set(addItem(tLastID = 209, "Lapotronic Crystal", "Reusable"));
        setElectricStats(32000 + tLastID, 25000000L, GT_Values.V[4], 4L, -3L, true);


        ItemList.ULV_Coil.set(addItem(tLastID = 210, "Ultra Low Voltage Coil", "Primitive Coil"));
	    ItemList.LV_Coil.set(addItem(tLastID = 211, "Low Voltage Coil", "Basic Coil"));
        ItemList.MV_Coil.set(addItem(tLastID = 212, "Medium Voltage Coil", "Good Coil"));
        ItemList.HV_Coil.set(addItem(tLastID = 213, "High Voltage Coil", "Advanced Coil"));
        ItemList.EV_Coil.set(addItem(tLastID = 214, "Extreme Voltage Coil", "Extreme Coil"));
        ItemList.IV_Coil.set(addItem(tLastID = 215, "Insane Voltage Coil", "Elite Coil"));
        ItemList.LuV_Coil.set(addItem(tLastID = 216, "Ludicrous Voltage Coil", "Master Coil"));
        ItemList.ZPM_Coil.set(addItem(tLastID = 217, "ZPM Voltage Coil", "Ultimate Coil"));
        ItemList.UV_Coil.set(addItem(tLastID = 218, "Ultimate Voltage Coil", "Super Coil"));
        ItemList.UHV_Coil.set(addItem(tLastID = 219, "Highly Ultimate Voltage Coil", "Infinite Coil"));
        ItemList.UEV_Coil.set(addItem(tLastID = 220, "Extremely Ultimate Voltage Coil", "Ultra Coil"));
        ItemList.UIV_Coil.set(addItem(tLastID = 221, "Insanely Ultimate Voltage Coil", "Insane Coil"));
        
        ItemList.FluidRegulator_LuV.set(addItem(tLastID = 180, "Fluid Regulator (LuV)", "Configuable up to 655360 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click"));
        ItemList.FluidRegulator_ZPM.set(addItem(tLastID = 181, "Fluid Regulator (ZPM)", "Configuable up to 2621440 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click"));
        ItemList.FluidRegulator_UV.set(addItem(tLastID = 182, "Fluid Regulator (UV)", "Configuable up to 10485760 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click"));
        
        GregTech_API.registerCover(ItemList.FluidRegulator_LuV.get(1L), new GT_MultiTexture(new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[6][0], TextureFactory.of(Textures.BlockIcons.OVERLAY_PUMP)}), new GT_Cover_FluidRegulator(32768));
        GregTech_API.registerCover(ItemList.FluidRegulator_ZPM.get(1L), new GT_MultiTexture(new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[7][0], TextureFactory.of(Textures.BlockIcons.OVERLAY_PUMP)}), new GT_Cover_FluidRegulator(131072));
        GregTech_API.registerCover(ItemList.FluidRegulator_UV.get(1L), new GT_MultiTexture(new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[8][0], TextureFactory.of(Textures.BlockIcons.OVERLAY_PUMP)}), new GT_Cover_FluidRegulator(524288));

        ItemList.Circuit_Naquaprocessor.set(addItem(tLastID = 168, "Naquaprocessor", "A Superconductor Circuit (UV)",  new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Naquacomputer.set(addItem(tLastID = 169, "Naquaprocessor Assembly", "An Infinite Circuit (UHV)",  new Object[]{OrePrefixes.circuit.get(Materials.Infinite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Naquasupercomputer.set(addItem(tLastID = 170, "Naqua Supercomputer", "A Bio Circuit (UEV)",  new Object[]{OrePrefixes.circuit.get(Materials.Bio), SubTag.NO_UNIFICATION}));

        ItemList.Circuit_Xenoprocessor.set(addItem(tLastID = 171, "Xenoprocessor", "An Infinite Circuit (UHV)",  new Object[]{OrePrefixes.circuit.get(Materials.Infinite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Xenocomputer.set(addItem(tLastID = 172, "Xenoprocessor Assembly", "A Bio Circuit (UEV)",  new Object[]{OrePrefixes.circuit.get(Materials.Bio), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Xenosupercomputer.set(addItem(tLastID = 173, "Xeno Supercomputer", "A Nano Circuit (UIV)",  new Object[]{OrePrefixes.circuit.get(Materials.Nano), SubTag.NO_UNIFICATION}));

        ItemList.Circuit_Cosmicprocessor.set(addItem(tLastID = 174, "Cosmic Processor", "A Bio Circuit (UEV)",  new Object[]{OrePrefixes.circuit.get(Materials.Bio), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Cosmiccomputer.set(addItem(tLastID = 175, "Cosmic Processor Assembly", "A Nano Circuit (UIV)",  new Object[]{OrePrefixes.circuit.get(Materials.Nano), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Cosmicsupercomputer.set(addItem(tLastID = 176, "Cosmic Supercomputer", "A Piko Circuit (UMV)",  new Object[]{OrePrefixes.circuit.get(Materials.Piko), SubTag.NO_UNIFICATION}));

        ItemList.NaquaBoardEmpty.set(addItem(tLastID = 177, "Portable Naquadah Reactor (Empty)", "Infinity energy for high consuption circuits"));
        ItemList.NaquaBoardFull.set(addItem(tLastID = 178, "Portable Naquadah Reactor (Full)", "Infinity energy for high consuption circuits"));

        ItemList.Circuit_Wafer_SoC5.set(addItem(tLastID = 183, "PSoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC5.set(addItem(tLastID = 184, "PSoC", "Pico System on a Chip"));

        ItemList.Circuit_Wafer_SoC6.set(addItem(tLastID = 185, "CSoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC6.set(addItem(tLastID = 186, "CSoC", "Chevron System on a Chip"));


    }

    @Override
    public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
        return aDoShowAllItems;
    }
}
