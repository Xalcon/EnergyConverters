package net.xalcon.energyconverters.common;

public enum ModGuis
{
    INVALID,
    GREGTECH_PRODUCER,
    ENERGY_BRIDGE;
    
    public static ModGuis fromId(int id)
    {
        if(id < 0 || id > values().length)
            return INVALID;
        
        return values()[id];
    }
}
