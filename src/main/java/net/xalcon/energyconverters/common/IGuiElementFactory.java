package net.xalcon.energyconverters.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IGuiElementFactory
{
    Object createElement(EntityPlayer player, World world, int x, int y, int z);
}
