package me.spindlyskit.journey.ui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PowersMenuWidget {
    protected static final Identifier TEXTURE = new Identifier("journey", "textures/gui/powers_menu.png");
}
