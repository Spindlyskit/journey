package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.network.ClientServerChannels;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class PowerTest extends Power {
    @Override
    public void use(PlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(0);
        ClientPlayNetworking.send(ClientServerChannels.SET_TIME, buf);
    }
}
