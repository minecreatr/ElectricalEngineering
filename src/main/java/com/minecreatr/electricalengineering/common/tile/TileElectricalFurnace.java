package com.minecreatr.electricalengineering.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity for an electrical furnace
 *
 * @author minecreatr
 */
public class TileElectricalFurnace extends ElectricalEngineeringTile {

    /**
     * The amount of progress on smelting the item, the field value is 2
     */
    private int smeltProgress;

    public TileElectricalFurnace(EnumFacing f){
        super(2, 64, 32000, f);
        smeltProgress = 0;
    }

    public void writeData(NBTTagCompound compound){
        compound.setInteger("cookTime", smeltProgress);
    }

    public void readData(NBTTagCompound compound){
        smeltProgress = compound.getInteger("cookTime");
    }

    @Override
    protected int getExtraFieldsCount(){
        return 1;
    }

    @Override
    protected void setExtraField(int id, int value){
        if (id==2){
            smeltProgress = value;
        }
    }

    @Override
    protected int getExtraField(int id){
        if (id==2){
            return smeltProgress;
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction){
        return (index==0&&getStackInSlot(index)==null);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction){
        return (index==1&&getStackInSlot(index)!=null);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        if (index==1){
            return false;
        }
        if (FurnaceRecipes.instance().getSmeltingResult(stack)!=null){
            return true;
        }
        return false;
    }

    public int[] getSlotsForFace(EnumFacing side){
        if (side==EnumFacing.UP){
            return new int[]{0};
        }
        else {
            return new int[]{1};
        }
    }

    public void update(){

    }

    @Override
    public String getName(){
        return "electricalFurnace.name";
    }
}
