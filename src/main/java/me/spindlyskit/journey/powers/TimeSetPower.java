package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.network.ClientServerChannels;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class TimeSetPower extends Power {
    private final int targetTime;

    public TimeSetPower(int time) {
        super();
        targetTime = time;
    }

    @Override
    public void use(PlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(targetTime);
        ClientPlayNetworking.send(ClientServerChannels.SET_TIME, buf);
    }
}
