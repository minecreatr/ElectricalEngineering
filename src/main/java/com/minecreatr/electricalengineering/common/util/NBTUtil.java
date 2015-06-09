package com.minecreatr.electricalengineering.common.util;

import com.minecreatr.electricalengineering.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;

/**
 * NBT utility
 *
 * @author minecreatr
 */
public class NBTUtil implements Reference{

    /**
     * NBT Number Constants
     */
    public static final int END = 0;
    public static final int BYTE = 1;
    public static final int SHORT = 2;
    public static final int INT = 3;
    public static final int LONG = 4;
    public static final int FLOAT = 5;
    public static final int DOUBLE = 6;
    public static final int BYTE_ARRAY = 7;
    public static final int STRING = 8;
    public static final int LIST = 9;
    public static final int COMPOUND = 10;
    public static final int INT_ARRAY = 11;

    /**
     * NBT String Constants
     */
    private static final String INVENTORY_LENGTH = "invLength";
    private static final String FACING = "facing";
    private static final String SLOT = "Slot";

    /**
     * Writes the Itemstack[] to an nbt compound
     * @param tagName The name of the tag that the inventory will be stored under
     * @param compound The NBT compound
     * @param inventory The Itemstack[]
     */
    public static void writeInventory(String tagName, NBTTagCompound compound, ItemStack[] inventory){
        NBTTagList list = new NBTTagList();
        if (inventory!=null){
            compound.setInteger(INVENTORY_LENGTH, inventory.length);
            for (int i=0;i<inventory.length;i++){
                if (inventory[i]!=null){
                    NBTTagCompound slotCompound = new NBTTagCompound();
                    slotCompound.setByte(SLOT, (byte)i);
                    inventory[i].writeToNBT(slotCompound);
                    list.appendTag(slotCompound);
                }
            }
        }
        compound.setTag(tagName, compound);
    }

    /**
     * Reads an Itemstack[] from an nbt compound
     * @param tagName The name of the tag to use
     * @param compound The Compound
     * @return The Itemstack[]
     */
    public static ItemStack[] readInventory(String tagName, NBTTagCompound compound){
        ItemStack[] inventory = new ItemStack[compound.getInteger(INVENTORY_LENGTH)];
        NBTTagList list = compound.getTagList(tagName, COMPOUND);
        for (int i=0;i<list.tagCount();i++){
            NBTTagCompound slotCompound = list.getCompoundTagAt(i);
            int slot = slotCompound.getByte(SLOT);
            if (slot>=0&&slot<inventory.length){
                inventory[slot] = ItemStack.loadItemStackFromNBT(slotCompound);
            }
        }
        return inventory;
    }

    /**
     * Writes an EnumFacing to nbt
     * @param facing the EnumFacing
     * @param compound the nbt compound
     */
    public static void writeEnumFacing(EnumFacing facing, NBTTagCompound compound){
        compound.setInteger(FACING, facing.getIndex());
    }

    /**
     * Reads an EnumFacing from nbt
     * @param compound the nbt compound
     * @return the EnumFacing
     */
    public static EnumFacing readEnumFacing(NBTTagCompound compound){
        return EnumFacing.getFront(compound.getInteger(FACING));
    }


}
