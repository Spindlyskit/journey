package me.spindlyskit.journey;

import me.spindlyskit.journey.access.PlayerEntityAccess;
import me.spindlyskit.journey.network.ServerClientChannels;
import me.spindlyskit.journey.network.ServerPacketRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;

public class JourneyMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Initialize client -> server packets
        ServerPacketRegistry.init();

        // Send powers menu state from server nbt to client
        ServerPlayConnectionEvents.JOIN.register(((handler, sender, server) -> {
            PacketByteBuf buf = PacketByteBufs.create();
            CompoundTag tag = new CompoundTag();
            ((PlayerEntityAccess) handler.player).getPowersMenuOptions().serialize(tag);
            buf.writeCompoundTag(tag);
            sender.sendPacket(ServerClientChannels.SET_POWERS_MENU_OPTIONS, buf);
        }));
    }
}
