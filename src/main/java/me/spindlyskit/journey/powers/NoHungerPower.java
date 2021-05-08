package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.network.ClientServerChannels;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class NoHungerPower extends Power {
    private final PowersMenuOptions options;

    public NoHungerPower(PowersMenuOptions options) {
        super();
        this.options = options;
    }

    @Override
    public void use(PlayerEntity player, boolean state) {
        options.setNoHunger(state);
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(state);
        ClientPlayNetworking.send(ClientServerChannels.SET_NO_HUNGER, buf);
    }

    @Override
    public boolean isToggled() {
        return options.isNoHunger();
    }

    @Override
    public int getTextureU() {
        return 1;
    }

    @Override
    public String getName() {
        return "hunger";
    }

    @Override
    public boolean isToggleable() {
        return true;
    }
}
