package com.minecreatr.electricalengineering.common.init;

import com.minecreatr.electricalengineering.Reference;
import com.minecreatr.electricalengineering.common.block.BlockElectricalFurnace;
import com.minecreatr.electricalengineering.common.tile.TileElectricalFurnace;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Used for registering all the mods tileentities
 *
 * @author minecreatr
 */
public class ModTiles implements Reference{

    public static void preInit(){
        GameRegistry.registerTileEntity(TileElectricalFurnace.class, "electricalFurnace");
    }
}
