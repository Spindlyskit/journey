package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.network.ClientServerChannels;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class GodModePower extends Power {
    private final PowersMenuOptions options;

    public GodModePower(PowersMenuOptions options) {
        super();
        this.options = options;
    }

    @Override
    public void use(PlayerEntity player, boolean state) {
        options.setGodmode(state);
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(state);
        ClientPlayNetworking.send(ClientServerChannels.SET_GOD_MODE, buf);
    }

    @Override
    public boolean isToggled() {
        return options.isGodmode();
    }

    @Override
    public String getName() {
        return "godmode";
    }

    @Override
    public boolean isToggleable() {
        return true;
    }
}
