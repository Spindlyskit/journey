package me.spindlyskit.journey.network;

import me.spindlyskit.journey.access.PlayerEntityAccess;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.nbt.CompoundTag;

@Environment(EnvType.CLIENT)
public final class ClientPacketRegistry {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(ServerClientChannels.SET_POWERS_MENU_OPTIONS, ((client, handler, buf, responseSender) -> {
            CompoundTag tag = buf.readCompoundTag();

            client.execute(() -> {
                PowersMenuOptions options = ((PlayerEntityAccess) client.player).getPowersMenuOptions();

                if (tag != null) {
                    options.deserialize(tag);
                }
            });
        }));
    }
}
