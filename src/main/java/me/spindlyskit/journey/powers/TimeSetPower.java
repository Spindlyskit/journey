package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.network.ClientServerChannels;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class TimeSetPower extends Power {
    private final int targetTime;
    private final String name;

    public TimeSetPower(int time, String name) {
        super();
        this.targetTime = time;
        this.name = name;
    }

    @Override
    public void use(PlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(targetTime);
        ClientPlayNetworking.send(ClientServerChannels.SET_TIME, buf);
    }

    @Override
    public int getTextureU() {
        return targetTime / 6000;
    }

    @Override
    public int getTextureV() {
        return 1;
    }

    @Override
    public String getName() {
        return name;
    }
}
