package com.minecreatr.electricalengineering.client;

import com.minecreatr.electricalengineering.ElectricalEngineering;
import com.minecreatr.electricalengineering.client.gui.GuiElectricalFurnace;
import com.minecreatr.electricalengineering.common.CommonProxy;
import com.minecreatr.electricalengineering.common.inventory.ContainerElectricalFurnace;
import com.minecreatr.electricalengineering.common.tile.ElectricalEngineeringTile;
import com.minecreatr.electricalengineering.common.tile.TileElectricalFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Client side proxy and gui handler
 *
 * @author minecreatr
 */
public class ClientProxy extends CommonProxy implements IGuiHandler{

    @Override
    public void preInit(ElectricalEngineering mod){
        NetworkRegistry.INSTANCE.registerGuiHandler(mod, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        BlockPos pos = new BlockPos(x, y, z);
        if (!(world.getTileEntity(pos) instanceof ElectricalEngineeringTile)){
            return null;
        }
        switch (ID){
            case 0: return new ContainerElectricalFurnace(player.inventory, (TileElectricalFurnace)world.getTileEntity(pos));
            default: return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        BlockPos pos = new BlockPos(x, y, z);
        if (!(world.getTileEntity(pos) instanceof ElectricalEngineeringTile)){
            return null;
        }
        switch (ID){
            case 0: return new GuiElectricalFurnace(player.inventory, (TileElectricalFurnace)world.getTileEntity(pos));
            default: return null;
        }
    }
}
