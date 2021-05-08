package me.spindlyskit.journey.powers;

import net.minecraft.entity.player.PlayerEntity;

public class ToggleablePowerTest extends Power {
    @Override
    public void use(PlayerEntity player, boolean state) {
        if (state) {
            System.out.println("Test power activated");
        } else {
            System.out.println("Test power deactivated");
        }
    }

    @Override
    public boolean isToggleable() {
        return true;
    }
}
