package me.spindlyskit.journey.ui;

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
    protected final int index;
    protected final List<PowerButton> buttons = Lists.newArrayList();
    protected static final MinecraftClient client = MinecraftClient.getInstance();

    protected PowerGroup(int baseX, int baseY, int index, boolean toggled) {
        super(baseX, baseY + (index * (HEIGHT + 1)), WIDTH, HEIGHT, toggled);
        setTextureUV(52, 2, 35, 0, PowersMenuWidget.TEXTURE);
        this.index = index;

        if (toggled) {
            x -= 2;
        }
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
    }

    @Override
    public void setToggled(boolean toggled) {
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
    protected void addButton(int x, int y, Power power) {
        int i = buttons.size() - 1;
        buttons.add(new PowerButton(x, y - (i * (PowerButton.BUTTON_SIZE + 2)), false, power));
    }

    protected void addButtons(int x, int y) {
    }
}
