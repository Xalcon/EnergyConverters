package net.xalcon.energyconverters.common.tiles;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import net.minecraft.util.EnumFacing;
import net.xalcon.energyconverters.EnergyConverters;

public class TileEntityConsumerGTCE extends TileEntityEnergyConvertersConsumer
{
    private IEnergyContainer energyContainer = new IEnergyContainer()
    {
        @Override
        public long acceptEnergyFromNetwork(EnumFacing enumFacing, long l, long l1)
        {
            return 0;
        }
    
        @Override
        public boolean inputsEnergy(EnumFacing enumFacing)
        {
            return false;
        }
    
        @Override
        public long changeEnergy(long l)
        {
            EnergyConverters.Log.info("changeEnergy({})", l);
            return 0;
        }
    
        @Override
        public long getEnergyStored()
        {
            return 0;
        }
    
        @Override
        public long getEnergyCapacity()
        {
            return (long) TileEntityConsumerGTCE.this.getBridgeEnergyStoredMax();
        }
    
        @Override
        public long getInputAmperage()
        {
            return 1;
        }
    
        @Override
        public long getInputVoltage()
        {
            return 1 << 14;
        }
    };
}