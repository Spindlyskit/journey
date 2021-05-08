package me.spindlyskit.journey.powers;

import net.minecraft.entity.player.PlayerEntity;

public class PowerTest extends Power {
    @Override
    public void use(PlayerEntity player, boolean state) {
        System.out.println("Non toggleable power used");
    }
}
