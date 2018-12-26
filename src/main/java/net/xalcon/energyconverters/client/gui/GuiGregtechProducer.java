package net.xalcon.energyconverters.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.xalcon.energyconverters.common.container.ContainerGregtechProducer;
import net.xalcon.energyconverters.common.tiles.TileEntityEnergyConvertersProducer;

public class GuiGregtechProducer extends GuiContainer
{
    private EntityPlayer player;
    private TileEntityEnergyConvertersProducer tile;
    
    public GuiGregtechProducer(EntityPlayer player, TileEntityEnergyConvertersProducer tile)
    {
        super(new ContainerGregtechProducer(player, tile));
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    
    }
}
