package me.spindlyskit.journey.ui.powersmenu;

import me.spindlyskit.journey.powers.Power;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class PowerButton extends ToggleButtonWidget {
    protected static final int BUTTON_SIZE = 24;
    protected final MinecraftClient client;
    private final Power power;

    protected PowerButton(int x, int y, boolean toggled, Power power) {
        super(x, y, BUTTON_SIZE, BUTTON_SIZE, toggled);
        setTextureUV(49, 30, 26, 0, PowersMenuWidget.TEXTURE);
        this.power = power;
        this.client = MinecraftClient.getInstance();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Render the button
        super.render(matrices, mouseX, mouseY, delta);

        // Find and render the icon
        client.getTextureManager().bindTexture(Power.ICON_TEXTURE);

        int iconOffset = (BUTTON_SIZE / 2) - 8;
        int iconX = x + iconOffset;
        int iconY = y + iconOffset;
        int iconU = power.getTextureU() * Power.ICON_SIZE;
        int iconV = power.getTextureV() * Power.ICON_SIZE;
        drawTexture(matrices, iconX, iconY, iconU, iconV, Power.ICON_SIZE, Power.ICON_SIZE);

        // Draw power tooltip
        if (client.currentScreen != null && isHovered()) {
            Text tooltipText = new TranslatableText("power." + power.getName() + ".name");
            client.currentScreen.renderTooltip(matrices, tooltipText, mouseX, mouseY);
        }
    }

    protected void onPress(PlayerEntity player) {
        setToggled(!isToggled() && power.isToggleable());
        power.use(player, isToggled());
    }
}
