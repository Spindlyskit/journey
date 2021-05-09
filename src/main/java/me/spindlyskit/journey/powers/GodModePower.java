package me.spindlyskit.journey.powers;

import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class GodModePower extends Power {
    private final PowersMenuOptions options;

    public GodModePower(PowersMenuOptions options) {
        super();
        this.options = options;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void use(PacketByteBuf buf, PlayerEntity player, boolean state) {
        options.setGodmode(state);
        buf.writeBoolean(state);
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
    public Powers getPowerEnum() {
        return Powers.GODMODE;
    }

    @Override
    public boolean isToggleable() {
        return true;
    }
}
