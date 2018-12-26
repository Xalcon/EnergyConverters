package net.xalcon.energyconverters.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.xalcon.energyconverters.common.tiles.TileEntityEnergyConvertersProducer;

public class ContainerGregtechProducer extends Container
{
    private EntityPlayer player;
    private TileEntityEnergyConvertersProducer tile;
    
    public ContainerGregtechProducer(EntityPlayer player, TileEntityEnergyConvertersProducer tile)
    {
        this.player = player;
        this.tile = tile;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return false;
    }
}
