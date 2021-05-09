package me.spindlyskit.journey.network;

import me.spindlyskit.journey.access.PlayerEntityAccess;
import me.spindlyskit.journey.powers.Power;
import me.spindlyskit.journey.powers.ServerPowerHandler;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

public final class ServerPacketRegistry {
    public static final Identifier EXECUTE_POWER = new Identifier("journey", "execute_power");
    public static final Identifier SET_POWERS_MENU_OPTIONS = new Identifier("journey", "set_powers_menu_options");

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(EXECUTE_POWER, ((server, player, handler, buf, responseSender) -> {
            Power.Powers power = buf.readEnumConstant(Power.Powers.class);
            ServerPowerHandler.handle(server, player, power, buf);
        }));

        ServerPlayNetworking.registerGlobalReceiver(SET_POWERS_MENU_OPTIONS, ((server, player, handler, buf, responseSender) -> {
            CompoundTag tag = buf.readCompoundTag();

            server.execute(() -> {
                PowersMenuOptions options = ((PlayerEntityAccess) player).getPowersMenuOptions();

                if (tag != null) {
                    options.deserialize(tag);
                }
            });
        }));
    }
}
