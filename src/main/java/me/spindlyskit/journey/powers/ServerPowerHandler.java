package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.access.PlayerEntityAccess;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * Handles running of powers on the server
 */
public final class ServerPowerHandler {
    public static void handle(MinecraftServer server, ServerPlayerEntity player, Power.Powers power, PacketByteBuf buf) {
        PowersMenuOptions options = ((PlayerEntityAccess) player).getPowersMenuOptions();

        switch (power) {
            case GODMODE:
                options.setGodmode(buf.readBoolean());
                break;
            case NO_HUNGER:
                options.setNoHunger(buf.readBoolean());
                break;
            case TIME:
                TimeSetPower.handleServer(server, player, buf.readInt());
                break;
            case WEATHER:
                WeatherSetPower.handleServer(server, player, buf.readEnumConstant(WeatherSetPower.WEATHER_TYPE.class));
                break;
        }
    }
}
