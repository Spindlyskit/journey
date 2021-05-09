package me.spindlyskit.journey.powers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.concurrent.ThreadLocalRandom;

public class WeatherSetPower extends Power {
    private final String name;
    public final WEATHER_TYPE weatherType;

    public WeatherSetPower(WEATHER_TYPE weather, String name) {
        super();
        this.name = name;
        this.weatherType = weather;
    }

    /**
     * Handle a weather set on the server
     */
    public static void handleServer(MinecraftServer server, ServerPlayerEntity player, WEATHER_TYPE weatherType) {
        int duration = ThreadLocalRandom.current().nextInt(5000, 11000);
        ServerWorld overworld = server.getOverworld();

        switch (weatherType) {
            case Clear:
                overworld.setWeather(duration, 0, false, false);
                break;
            case Rain:
                overworld.setWeather(0, duration, true, false);
                break;
            case Thunder:
                overworld.setWeather(0, duration, true, true);
                break;
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void use(PacketByteBuf buf, PlayerEntity player, boolean state) {
        buf.writeEnumConstant(weatherType);
    }

    @Override
    public int getTextureU() {
        switch (weatherType) {
            case Clear:
                return 0;
            case Rain:
                return 1;
            case Thunder:
                return 2;
        }
        return 0;
    }

    @Override
    public int getTextureV() {
        return 2;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Powers getPowerEnum() {
        return Powers.WEATHER;
    }

    public enum WEATHER_TYPE {
        Clear,
        Rain,
        Thunder
    }
}
