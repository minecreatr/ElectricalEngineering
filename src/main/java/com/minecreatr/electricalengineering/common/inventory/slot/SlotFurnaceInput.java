package com.minecreatr.electricalengineering.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Slot for electrical furnace input
 *
 * @author minecreatr
 */
public class SlotFurnaceInput extends Slot {

    public SlotFurnaceInput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack){
        return FurnaceRecipes.instance().getSmeltingResult(stack)!=null;
    }

    public int getItemStackLimit(ItemStack stack) {
        return 64;
    }
}
