package me.spindlyskit.journey.ui;

import me.spindlyskit.journey.powers.Power;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class PowerButton extends ToggleButtonWidget {
    protected static final int BUTTON_SIZE = 24;
    private final Power power;

    protected PowerButton(int x, int y, boolean toggled, Power power) {
        super(x, y, BUTTON_SIZE, BUTTON_SIZE, toggled);
        setTextureUV(49, 30, 26, 0, PowersMenuWidget.TEXTURE);
        this.power = power;
    }

    protected void onPress(PlayerEntity player) {
        if (isToggled()) {
            power.deactivate(player);
            setToggled(false);
        } else {
            power.use(player);
            setToggled(power.isToggleable());
        }
    }
}
