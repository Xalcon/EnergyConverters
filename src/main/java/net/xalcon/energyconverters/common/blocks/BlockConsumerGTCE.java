package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.xalcon.energyconverters.common.tiles.TileEntityConsumerGTCE;

import javax.annotation.Nullable;

public class BlockConsumerGTCE extends BlockBase implements ITileEntityProvider
{
    public BlockConsumerGTCE()
    {
        super(Material.GROUND, "consumer_gtce");
    }
    
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityConsumerGTCE();
    }
}
