package net.xalcon.energyconverters.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class BlockEnergyBridge extends BlockBase
{
    public BlockEnergyBridge()
    {
        super(Material.IRON, "energy_bridge");
    }

    @Override
    public boolean isVisuallyOpaque()
    {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
