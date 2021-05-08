package me.spindlyskit.journey.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.world.ServerWorld;

public final class ServerPacketRegistry {
    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(ClientServerChannels.SET_TIME, ((server, player, handler, buf, responseSender) -> {
            int time = buf.readInt();

            server.execute(() -> {
                for (ServerWorld world : server.getWorlds()) {
                    world.setTimeOfDay(time);
                }
            });
        }));
    }
}
