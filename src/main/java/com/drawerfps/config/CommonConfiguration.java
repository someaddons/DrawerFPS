package com.drawerfps.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfiguration
{
    public final ForgeConfigSpec.ConfigValue<Integer> renderRange;
    public final ForgeConfigSpec                      ForgeConfigSpecBuilder;

    protected CommonConfiguration(final ForgeConfigSpec.Builder builder)
    {
        builder.push("Drawer Fps settings");

        builder.comment("Range at which the drawers should render their item. Default = 10 blocks");
        renderRange = builder.defineInRange("renderRange", 10, 1, 500);

        // Escapes the current category level
        builder.pop();
        ForgeConfigSpecBuilder = builder.build();
    }
}
