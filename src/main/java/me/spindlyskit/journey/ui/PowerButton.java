package me.spindlyskit.journey.ui;

import me.spindlyskit.journey.powers.Power;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
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

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Render the button
        super.render(matrices, mouseX, mouseY, delta);

        // Find and render the icon
        MinecraftClient.getInstance().getTextureManager().bindTexture(Power.ICON_TEXTURE);

        int iconOffset = (BUTTON_SIZE / 2) - 8;
        int iconX = x + iconOffset;
        int iconY = y + iconOffset;
        int iconU = power.getTextureU() * Power.ICON_SIZE;
        int iconV = power.getTextureV() * Power.ICON_SIZE;
        drawTexture(matrices, iconX, iconY, iconU, iconV, Power.ICON_SIZE, Power.ICON_SIZE);
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
