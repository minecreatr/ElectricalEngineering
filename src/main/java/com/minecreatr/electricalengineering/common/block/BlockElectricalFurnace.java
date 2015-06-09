package com.minecreatr.electricalengineering.common.block;

import com.minecreatr.electricalengineering.ElectricalEngineering;
import com.minecreatr.electricalengineering.common.tile.TileElectricalFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Electrical furnace block
 *
 * @author minecreatr
 */
public class BlockElectricalFurnace extends BlockElectricalEngineering{

    public BlockElectricalFurnace(){
        super(Material.circuits);
        setUnlocalizedName("blockElectricalFurnace");
    }

    public TileEntity createNewTileEntity(World world, int meta){
        return new TileElectricalFurnace(EnumFacing.NORTH);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.isSneaking()){
            return false;
        }
        if (worldIn.getTileEntity(pos)!=null){
            if (worldIn.getTileEntity(pos) instanceof TileElectricalFurnace){
                playerIn.openGui(ElectricalEngineering.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
        }
        return false;
    }
}
