package me.spindlyskit.journey.ui.powersmenu;

import com.google.common.collect.Lists;
import me.spindlyskit.journey.powers.Power;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ToggleButtonWidget;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PowerGroup extends ToggleButtonWidget {
    protected static final int WIDTH = 35;
    protected static final int HEIGHT = 26;
    protected final byte index;
    // X and Y coordinates of the top left of the first tab button before modifications
    protected final int baseX;
    protected final int baseY;

    private static final int BUTTON_X_OFFSET = 41;
    private static final int BUTTON_Y_OFFSET = 8;
    protected final List<PowerButton> buttons = Lists.newArrayList();
    protected static final MinecraftClient client = MinecraftClient.getInstance();

    protected PowerGroup(int baseX, int baseY, int index) {
        super(baseX, baseY + (index * (HEIGHT + 1)), WIDTH, HEIGHT, false);
        setTextureUV(52, 2, 35, 0, PowersMenuWidget.TEXTURE);
        this.index = (byte) index;
        this.baseX = baseX;
        this.baseY = baseY;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (isToggled()) {
            for (PowerButton button : buttons) {
                if (button.mouseClicked(mouseX, mouseY, mouseButton)) {
                    button.onPress(client.player);
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void setPos(int baseX, int baseY) {
        this.x = baseX - (isToggled() ? 2 : 0);
        this.y = baseY + (index * (HEIGHT + 1));

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setPos(baseX + BUTTON_X_OFFSET, baseY + (i * (PowerButton.BUTTON_SIZE + 2)) + BUTTON_Y_OFFSET);
        }
    }

    @Override
    public void setToggled(boolean toggled) {
        // Make the active tab's wider texture render correctly
        if (toggled != isToggled()) {
            if (toggled) {
                x -= 2;
            } else {
                x += 2;
            }
        }

        super.setToggled(toggled);
    }

    /**
     * Create a new power toggle button
     */
    protected void addButton(Power power) {
        int i = buttons.size();
        buttons.add(new PowerButton(baseX + BUTTON_X_OFFSET, baseY + (i * (PowerButton.BUTTON_SIZE + 2)) + BUTTON_Y_OFFSET,
                false, power));
    }

    protected void addButtons() {
    }
}
