package com.minecreatr.electricalengineering.common.block;

import cofh.api.block.IDismantleable;
import com.minecreatr.electricalengineering.ElectricalEngineering;
import com.minecreatr.electricalengineering.common.tile.ElectricalEngineeringTile;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Base electrical engineering block
 *
 * @author minecreatr
 */
public class BlockElectricalEngineering extends BlockContainer implements IDismantleable{

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockElectricalEngineering(Material m){
        super(m);
        setCreativeTab(ElectricalEngineering.tab);
        setupStates();
    }

    public TileEntity createNewTileEntity(World world, int meta){
        return null;
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops){
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        BlockPos pos = new BlockPos(x, y, z);
        list.add(new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos))));
        if (world.getTileEntity(pos) instanceof ElectricalEngineeringTile){
            ElectricalEngineeringTile tile = (ElectricalEngineeringTile) world.getTileEntity(pos);
            list.addAll(Arrays.asList(tile.getContents()));
        }
        return list;
    }

    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z){
        return true;
    }


    @Override
    public BlockState createBlockState(){
        return new BlockState(this, new IProperty[]{FACING});
    }

    private void setupStates(){
        this.setDefaultState(getBlockState().getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        EnumFacing f = EnumFacing.getFront(meta);
        return this.getDefaultState().withProperty(FACING, f);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
}
