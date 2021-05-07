package me.spindlyskit.journey.powers;

import net.minecraft.entity.player.PlayerEntity;

public class ToggleablePowerTest extends Power {
    @Override
    public void use(PlayerEntity player) {
        System.out.println("Test power activated");
    }

    @Override
    public void deactivate(PlayerEntity player) {
        System.out.println("Test power deactivated");
    }

    @Override
    public boolean isToggleable() {
        return true;
    }
}
