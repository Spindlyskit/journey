package me.spindlyskit.journey.powers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public abstract class Power {
    public static final Identifier ICON_TEXTURE = new Identifier("journey", "textures/gui/powers.png");
    public static final int ICON_SIZE = 16;

    /**
     * Called when the power is used or activated
     */
    public abstract void use(PlayerEntity player);

    /**
     * Called when a toggleable power is deactivated
     */
    public void deactivate(PlayerEntity player) {}

    public boolean isToggleable() {
        return false;
    }

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
}
