package com.minecreatr.electricalengineering.common.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import com.minecreatr.electricalengineering.ElectricalEngineering;
import com.minecreatr.electricalengineering.common.util.NBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

/**
 * Base class for all of the electrical engineering tile entities
 *
 * @author minecreatr
 */
public abstract class ElectricalEngineeringTile extends TileEntity implements ISidedInventory, IEnergyProvider, IEnergyReceiver, IUpdatePlayerListBox{


    protected ItemStack[] inventory;

    protected int stackLimit;

    private EnumFacing facing;

    protected EnergyStorage energyStorage;

    public ElectricalEngineeringTile(int inventorySize, int energyCapacity, EnumFacing facing){
        this(inventorySize, 64, energyCapacity, facing);
    }

    public ElectricalEngineeringTile(int inventorySize, int stackLimit, int energyCapacity, EnumFacing facing){
        inventory = new ItemStack[inventorySize];
        this.stackLimit = stackLimit;
        energyStorage = new EnergyStorage(energyCapacity);
        this.facing = facing;
    }

    public abstract void writeData(NBTTagCompound compound);

    public abstract void readData(NBTTagCompound compound);

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTUtil.writeInventory("inventory", compound, inventory);
        energyStorage.writeToNBT(compound);
        NBTUtil.writeEnumFacing(facing, compound);
        this.writeData(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        inventory = NBTUtil.readInventory("inventory", compound);
        energyStorage.readFromNBT(compound);
        facing = NBTUtil.readEnumFacing(compound);
        this.readData(compound);
    }

    /**
     * Get the direction the tile is facing
     * @return The EnumFacing
     */
    public EnumFacing getEnumFacing(){
        return this.facing;
    }

    public int getSizeInventory(){
        return inventory.length;
    }

    public ItemStack getStackInSlot(int index){
        return inventory[index];
    }

    public ItemStack decrStackSize(int slot, int amt){
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    public ItemStack getStackInSlotOnClosing(int index){
        return getStackInSlot(index);
    }

    public void setInventorySlotContents(int index, ItemStack stack){
        inventory[index] = stack;
    }

    public int getInventoryStackLimit(){
        return stackLimit;
    }

    public void markDirty(){

    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(pos, 1, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    public boolean isUseableByPlayer(EntityPlayer player){
        return true;
    }

    public void openInventory(EntityPlayer player){

    }

    public void closeInventory(EntityPlayer player){

    }

    //Field 0 is power, field 1 is power capacity
    public int getField(int id) {
        if (id==0){
            return energyStorage.getEnergyStored();
        }
        else if (id==1){
            return energyStorage.getMaxEnergyStored();
        }
        else {
            return getExtraField(id);
        }
    }

    /**
     * Gets the extra field with the id
     * @param id The Id
     * @return the value of the extra field
     */
    protected int getExtraField(int id){
        return 0;
    }


    public void setField(int id, int value) {
        if (id==0){
            energyStorage.setEnergyStored(value);
        }
        else if (id==1){
            energyStorage.setCapacity(value);
        }
        else {
            setExtraField(id, value);
        }
    }

    /**
     * Sets any extra field
     * @param id field id
     * @param value field value
     */
    protected void setExtraField(int id, int value){

    }

    public int getFieldCount() {
        return 2;
    }

    /**
     * How many extra fields besides power
     * @return How many extra fields
     */
    protected int getExtraFieldsCount(){
        return 0;
    }

    public void clear(){
        for (int i=0;i<inventory.length;i++){
            inventory[i] = null;
        }
    }

    public String getName(){
        return "electricalEngineeringTile";
    }

    public boolean hasCustomName(){
        return false;
    }

    public IChatComponent getDisplayName(){
        return new ChatComponentTranslation(getName());
    }

    public ItemStack[] getContents(){
        return inventory;
    }

    public boolean isItemValidForSlot(int index, ItemStack stack){
        return true;
    }


    public boolean canConnectEnergy(EnumFacing facing){
        return true;
    }

    public int[] getSlotsForFace(EnumFacing side){
        return new int[]{0};
    }

    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate){
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    public int getEnergyStored(EnumFacing facing){
        return energyStorage.getEnergyStored();
    }

    public int getMaxEnergyStored(EnumFacing facing){
        return energyStorage.getMaxEnergyStored();
    }

    public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate){
        return energyStorage.extractEnergy(maxExtract, simulate);
    }
}
