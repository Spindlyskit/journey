package me.spindlyskit.journey.network;

import me.spindlyskit.journey.access.PlayerEntityAccess;
import me.spindlyskit.journey.powers.WeatherSetPower;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;

import java.util.concurrent.ThreadLocalRandom;

public final class ServerPacketRegistry {
    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(ClientServerChannels.SET_TIME, ((server, player, handler, buf, responseSender) -> {
            int time = buf.readInt();

            server.execute(() -> {
                for (ServerWorld world : server.getWorlds()) {
                    // Add to the current time so we don't reset local difficulty
                    int currentTime = (int) world.getTimeOfDay() % 24000;
                    int toAdd = time - (currentTime > time ? currentTime + 24 : currentTime);
                    world.setTimeOfDay(world.getTimeOfDay() + toAdd);
                }
            });
        }));

        ServerPlayNetworking.registerGlobalReceiver(ClientServerChannels.SET_WEATHER, (((server, player, handler, buf, responseSender) -> {
            WeatherSetPower.WEATHER_TYPE weatherType = buf.readEnumConstant(WeatherSetPower.WEATHER_TYPE.class);

            server.execute(() -> {
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
            });
        })));

        ServerPlayNetworking.registerGlobalReceiver(ClientServerChannels.SET_GOD_MODE, (((server, player, handler, buf, responseSender) -> {
            ((PlayerEntityAccess) player).getPowersMenuOptions().setGodmode(buf.readBoolean());
        })));

        ServerPlayNetworking.registerGlobalReceiver(ClientServerChannels.SET_NO_HUNGER, ((server, player, handler, buf, responseSender) -> {
            ((PlayerEntityAccess) player).getPowersMenuOptions().setNoHunger(buf.readBoolean());
        }));

        ServerPlayNetworking.registerGlobalReceiver(ClientServerChannels.SET_POWERS_MENU_OPTIONS, (((server, player, handler, buf, responseSender) -> {
            CompoundTag tag = buf.readCompoundTag();

            server.execute(() -> {
                PowersMenuOptions options = ((PlayerEntityAccess) player).getPowersMenuOptions();

                if (tag != null) {
                    options.deserialize(tag);
                }
            });
        })));
    }
}
