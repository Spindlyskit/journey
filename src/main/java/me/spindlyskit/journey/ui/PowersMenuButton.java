package me.spindlyskit.journey.ui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
/*
 * Button to toggle access to the journey mode powers window
 */
public class PowersMenuButton extends TexturedButtonWidget {
    private static final Identifier TEXTURE = new Identifier("journey", "textures/gui/powers_menu_button.png");
    private static final int X_OFFSET = 126;
    private static final int Y_OFFSET = -22;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 18;
    private static final int HOVERED_V_OFFSET = 19;

    public PowersMenuButton(int x, int y, PressAction pressAction) {
        super(x + X_OFFSET, y + Y_OFFSET, WIDTH, HEIGHT, 0, 0, HOVERED_V_OFFSET, TEXTURE, pressAction);
    }

    public void resetPos(int x, int y) {
        this.setPos(x + X_OFFSET, y+ Y_OFFSET);
    }
}
