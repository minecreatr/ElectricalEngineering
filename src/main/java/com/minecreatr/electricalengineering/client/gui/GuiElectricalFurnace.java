package com.minecreatr.electricalengineering.client.gui;

import com.minecreatr.electricalengineering.common.inventory.ContainerElectricalFurnace;
import com.minecreatr.electricalengineering.common.tile.TileElectricalFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

/**
 * Gui for the electrical furnace
 *
 * @author minecreatr
 */
public class GuiElectricalFurnace extends GuiContainer{

    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/furnace.png");

    private TileElectricalFurnace tile;
    private InventoryPlayer playerInventory;

    public GuiElectricalFurnace(InventoryPlayer player, TileElectricalFurnace inventory){
        super(new ContainerElectricalFurnace(player, inventory));
        this.tile = inventory;
        this.playerInventory = player;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tile.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

    }
}
