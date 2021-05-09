package me.spindlyskit.journey.powers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class TimeSetPower extends Power {
    private final int targetTime;
    private final String name;

    public TimeSetPower(int time, String name) {
        super();
        this.targetTime = time;
        this.name = name;
    }

    /**
     * Handle a time set on the server
     */
    public static void handleServer(MinecraftServer server, ServerPlayerEntity player, int time) {
        for (ServerWorld world : server.getWorlds()) {
            // Add to the current time so we don't reset local difficulty
            int currentTime = (int) world.getTimeOfDay() % 24000;
            int toAdd = time - (currentTime > time ? currentTime + 24 : currentTime);
            world.setTimeOfDay(world.getTimeOfDay() + toAdd);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void use(PacketByteBuf buf, PlayerEntity player, boolean state) {
        buf.writeInt(targetTime);
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

    @Override
    public Powers getPowerEnum() {
        return Powers.TIME;
    }
}
