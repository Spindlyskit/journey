package me.spindlyskit.journey.ui;

import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ToggleButtonWidget;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PowersMenuGroup extends ToggleButtonWidget {
    // TODO: Extend class and move to constructor
    private static final int BUTTON_COUNT = 4;
    protected static final int WIDTH = 35;
    protected static final int HEIGHT = 26;
    protected final int index;
    protected final List<PowerToggleButton> buttons = Lists.newArrayList();

    protected PowersMenuGroup(int baseX, int baseY, int index, boolean toggled) {
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
            for (PowerToggleButton button : buttons) {
                if (button.mouseClicked(mouseX, mouseY, mouseButton)) {
                    button.setToggled(!button.isToggled());
                    button.pressAction.onPress(button);
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
     * Create BUTTON_COUNT power toggle buttons
     */
    protected void addButtons(int x, int y) {
        for (int i = 0; i < BUTTON_COUNT; i++) {
            buttons.add(new PowerToggleButton(x, y + (i * (PowerToggleButton.BUTTON_SIZE + 2)), false, (buttonWidget) -> System.out.println("Button pressed")));
        }
    }
}
