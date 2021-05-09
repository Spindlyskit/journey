package me.spindlyskit.journey.powers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public abstract class Power {
    public static final Identifier ICON_TEXTURE = new Identifier("journey", "textures/gui/powers.png");
    public static final int ICON_SIZE = 16;
    public static final Identifier IDENTIFIER = new Identifier("journey", "power_unnamed");

    /**
     * Called when the power is used or activated
     */
    @Environment(EnvType.CLIENT)
    public abstract void use(PacketByteBuf buf, PlayerEntity player, boolean state);

    public boolean isToggleable() {
        return false;
    }

    public boolean isToggled() { return false; }

    /**
     * Get the u offset of the power icon texture (should be multiplied by 16 to find the icon)
     */
    public int getTextureU() { return 0; }

    /**
     * Get the v offset of the power icon texture (should be multiplied by 16 to find the icon)
     */
    public int getTextureV() { return 0; }

    /**
     * Get the powers non translated name
     */
    public String getName() { return "unnamed"; }

    public abstract Powers getPowerEnum();

    public enum Powers {
        GODMODE,
        NO_HUNGER,
        TIME,
        WEATHER
    }
}
