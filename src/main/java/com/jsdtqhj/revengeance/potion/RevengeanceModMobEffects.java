package com.jsdtqhj.revengeance.potion;

import com.jsdtqhj.revengeance.RevengeanceMod;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class RevengeanceModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, RevengeanceMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> RAGE_EFFECT = MOB_EFFECTS.register("rage",
            RageMobEffect::new);

    public static final DeferredHolder<MobEffect, MobEffect> ADRENALINE_EFFECT = MOB_EFFECTS.register("adrenaline",
            AdrenalineMobEffect::new);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
