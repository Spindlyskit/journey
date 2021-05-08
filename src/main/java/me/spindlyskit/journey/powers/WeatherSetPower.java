package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.network.ClientServerChannels;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class WeatherSetPower extends Power {
    private final String name;
    public final WEATHER_TYPE weatherType;

    public WeatherSetPower(WEATHER_TYPE weather, String name) {
        super();
        this.name = name;
        this.weatherType = weather;
    }

    @Override
    public void use(PlayerEntity player, boolean state) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeEnumConstant(weatherType);
        ClientPlayNetworking.send(ClientServerChannels.SET_WEATHER, buf);
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

    public enum WEATHER_TYPE {
        Clear,
        Rain,
        Thunder
    }
}
