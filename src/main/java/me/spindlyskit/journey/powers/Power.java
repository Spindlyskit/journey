package me.spindlyskit.journey.powers;

import net.minecraft.entity.player.PlayerEntity;

public abstract class Power {
    /**
     * Called when the power is used or activated
     */
    public abstract void use(PlayerEntity player);

    /**
     * Called when a toggleable power is deactivated
     */
    public void deactivate(PlayerEntity player) {
    }

    public boolean isToggleable() {
        return false;
    }
}
