package com.minecreatr.electricalengineering.common.init;

import com.minecreatr.electricalengineering.Reference;
import com.minecreatr.electricalengineering.common.block.BlockElectricalFurnace;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * This holds all the instances of the different mod blocks
 *
 * @author minecreatr
 */
public class ModBlocks implements Reference{

    public static BlockElectricalFurnace electricalFurnace;

    public static void preInit(){
        electricalFurnace = new BlockElectricalFurnace();
        GameRegistry.registerBlock(electricalFurnace, "blockElectricalFurnace");
    }
}
