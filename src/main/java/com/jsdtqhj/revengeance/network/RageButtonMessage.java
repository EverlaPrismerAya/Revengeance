package com.jsdtqhj.revengeance.network;

import com.jsdtqhj.revengeance.RevengeanceMod;
import com.jsdtqhj.revengeance.procedures.RageButtonClickProcedure;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import io.netty.buffer.ByteBuf;

public record RageButtonMessage(int eventType, int pressedms) implements CustomPacketPayload {
    
    public static final CustomPacketPayload.Type<RageButtonMessage> TYPE = 
        new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(RevengeanceMod.MODID, "rage_button"));

    public static final StreamCodec<ByteBuf, RageButtonMessage> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.VAR_INT, RageButtonMessage::eventType,
        ByteBufCodecs.VAR_INT, RageButtonMessage::pressedms,
        RageButtonMessage::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(RageButtonMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                pressAction(player, message.eventType, message.pressedms);
            }
        }).exceptionally(e -> {
            context.disconnect(net.minecraft.network.chat.Component.translatable("revengeance.network.error"));
            return null;
        });
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        // 安全措施，防止任意区块生成
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {
            RageButtonClickProcedure.execute(entity);
        }
    }
}