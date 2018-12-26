package net.xalcon.energyconverters.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.xalcon.energyconverters.client.gui.GuiGregtechProducer;
import net.xalcon.energyconverters.common.container.ContainerGregtechProducer;
import net.xalcon.energyconverters.common.tiles.TileEntityEnergyConvertersProducer;

import javax.annotation.Nullable;

public class ModGuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ModGuis.fromId(id))
        {
            case GREGTECH_PRODUCER:
                return new ContainerGregtechProducer(player, (TileEntityEnergyConvertersProducer) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }
    
    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ModGuis.fromId(id))
        {
            case GREGTECH_PRODUCER:
                return new GuiGregtechProducer(player, (TileEntityEnergyConvertersProducer) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }
}
