package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class NoHungerPower extends Power {
    private final PowersMenuOptions options;

    public NoHungerPower(PowersMenuOptions options) {
        super();
        this.options = options;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void use(PacketByteBuf buf, PlayerEntity player, boolean state) {
        options.setNoHunger(state);
        buf.writeBoolean(state);
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
    public Powers getPowerEnum() {
        return Powers.NO_HUNGER;
    }

    @Override
    public boolean isToggleable() {
        return true;
    }
}
