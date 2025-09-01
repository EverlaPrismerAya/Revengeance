package com.jsdtqhj.revengeance;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // === Rage System Performance Settings ===
    private static final ModConfigSpec.IntValue RAGE_UPDATE_INTERVAL = BUILDER
            .comment("How often (in ticks) to scan for hostile entities for rage charging. Lower = more accurate but worse performance. (1-20)")
            .defineInRange("rageUpdateInterval", 3, 1, 20);
    
    private static final ModConfigSpec.IntValue RAGE_CACHE_DURATION = BUILDER
            .comment("How long (in ticks) to cache hostile entity scan results. Should be >= rageUpdateInterval for best performance.")
            .defineInRange("rageCacheDuration", 5, 1, 40);
    
    private static final ModConfigSpec.DoubleValue RAGE_DETECTION_RADIUS = BUILDER
            .comment("Maximum distance to detect hostile entities for rage charging (8-64 blocks)")
            .defineInRange("rageDetectionRadius", 32.0, 8.0, 64.0);
    
    private static final ModConfigSpec.DoubleValue RAGE_INNER_RADIUS = BUILDER
            .comment("Distance for maximum rage charging speed (4-32 blocks, should be < rageDetectionRadius)")
            .defineInRange("rageInnerRadius", 8.0, 4.0, 32.0);

    static final ModConfigSpec SPEC = BUILDER.build();

    // Performance settings
    public static int rageUpdateInterval;
    public static int rageCacheDuration; 
    public static double rageDetectionRadius;
    public static double rageInnerRadius;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    public static void onLoad(final ModConfigEvent event)
    {
        rageUpdateInterval = RAGE_UPDATE_INTERVAL.get();
        rageCacheDuration = RAGE_CACHE_DURATION.get();
        rageDetectionRadius = RAGE_DETECTION_RADIUS.get();
        rageInnerRadius = RAGE_INNER_RADIUS.get();
    }
}
