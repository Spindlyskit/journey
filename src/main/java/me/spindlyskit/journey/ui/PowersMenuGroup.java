package me.spindlyskit.journey.ui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ToggleButtonWidget;

@Environment(EnvType.CLIENT)
public class PowersMenuGroup extends ToggleButtonWidget {
    protected static final int WIDTH = 35;
    protected static final int HEIGHT = 26;
    private final int index;

    protected PowersMenuGroup(int baseX, int baseY, int index, boolean toggled) {
        super(baseX, baseY + (index * (HEIGHT + 1)), WIDTH, HEIGHT, toggled);
        setTextureUV(49, 2, 36, 0, PowersMenuWidget.TEXTURE);
        this.index = index;
    }
}
