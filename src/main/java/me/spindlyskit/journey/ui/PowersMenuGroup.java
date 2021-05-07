package me.spindlyskit.journey.ui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class PowersMenuGroup extends ToggleButtonWidget {
    protected static final int WIDTH = 35;
    protected static final int HEIGHT = 26;
    private final int index;

    protected PowersMenuGroup(int baseX, int baseY, int index, boolean toggled) {
        super(baseX, baseY + (index * (HEIGHT + 1)), WIDTH, HEIGHT, toggled);
        setTextureUV(52, 2, 35, 0, PowersMenuWidget.TEXTURE);
        this.index = index;

        if (toggled) {
            x -= 2;
        }
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
}
