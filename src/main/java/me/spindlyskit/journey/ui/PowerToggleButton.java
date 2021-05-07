package me.spindlyskit.journey.ui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;

@Environment(EnvType.CLIENT)
public class PowerToggleButton extends ToggleButtonWidget {
    protected static final int BUTTON_SIZE = 24;
    protected final PowerToggleButton.PressAction pressAction;

    protected PowerToggleButton(int x, int y, boolean toggled, PowerToggleButton.PressAction pressAction) {
        super(x, y, BUTTON_SIZE, BUTTON_SIZE, toggled);
        setTextureUV(49, 30, 26, 0, PowersMenuWidget.TEXTURE);
        this.pressAction = pressAction;
    }

    public interface PressAction {
        void onPress(PowerToggleButton button);
    }
}
