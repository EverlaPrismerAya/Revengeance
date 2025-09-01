package com.jsdtqhj.revengeance;


import com.jsdtqhj.revengeance.attributes.RevengeanceModAttributes;
import com.jsdtqhj.revengeance.potion.RevengeanceModMobEffects;
import com.jsdtqhj.revengeance.sounds.RevengeanceModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(RevengeanceMod.MODID)
public class RevengeanceMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "revengeance";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public RevengeanceMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerPayloads);
        modEventBus.addListener(com.jsdtqhj.revengeance.init.RevengeanceModKeyMappings::registerKeyMappings);
        modEventBus.addListener(Config::onLoad);
        modEventBus.addListener(RevengeanceModAttributes::addAttributes);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // 注册属性
        RevengeanceModAttributes.REGISTRY.register(modEventBus);
        
        // 注册药水效果
        RevengeanceModMobEffects.register(modEventBus);

        // 注册音效
        RevengeanceModSounds.REGISTRY.register(modEventBus);

    }

    // Network payload registration
    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        
        registrar.playToServer(
            com.jsdtqhj.revengeance.network.RageButtonMessage.TYPE,
            com.jsdtqhj.revengeance.network.RageButtonMessage.STREAM_CODEC,
            com.jsdtqhj.revengeance.network.RageButtonMessage::handle
        );
        
        registrar.playToServer(
            com.jsdtqhj.revengeance.network.AdrenalineButtonMessage.TYPE,
            com.jsdtqhj.revengeance.network.AdrenalineButtonMessage.STREAM_CODEC,
            com.jsdtqhj.revengeance.network.AdrenalineButtonMessage::handle
        );
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            // 在这里可以添加需要在commonSetup中执行的代码
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
