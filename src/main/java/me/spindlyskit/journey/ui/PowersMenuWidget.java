package me.spindlyskit.journey.ui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import me.spindlyskit.journey.ui.powergroups.PersonalPowersGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PowersMenuWidget extends DrawableHelper implements Drawable {
    protected static final Identifier TEXTURE = new Identifier("journey", "textures/gui/powers_menu.png");
    public static final int WIDTH = 46;
    public static final int HEIGHT = 147;
    protected static final int INVENTORY_HEIGHT = 166;
    protected static final int MENU_U_OFFSET = 1;
    protected static final int MENU_V_OFFSET = 1;

    protected MinecraftClient client;
    private int x;
    private int y;
    private boolean open = false;
    private final List<PowerGroup> groups = Lists.newArrayList();
    private int focusedGroup = 0;

    public void initialize(int parentWidth, int parentHeight, MinecraftClient client) {
        this.client = client;
        x = (parentWidth) / 2 - 90;
        y = (parentHeight - HEIGHT - (INVENTORY_HEIGHT - HEIGHT)) / 2;

        int baseX = x - PowerGroup.WIDTH + 5;
        int baseY = y + 3;

        // Create groups if they don't already exist
        // if initialize is called while groups exist (eg. a resize occurred) simply move the old buttons
        if (groups.isEmpty()) {
            {
                PowerGroup group = new PersonalPowersGroup(baseX, baseY, 0, true);
                group.addButtons(baseX + 41, y + 11);
                groups.add(group);
            }

            for (int i = 1; i < 3; i++) {
                PowerGroup group = new PowerGroup(baseX, baseY, i, false);
                group.addButtons(baseX + 41, y + 11);
                groups.add(group);
            }
        } else {
            for (PowerGroup group : groups) {
                group.setPos(baseX, baseY);
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (open) {
            RenderSystem.pushMatrix();
            RenderSystem.translatef(0.0f, 0.0f, 100.0f);
            this.client.getTextureManager().bindTexture(TEXTURE);

            drawTexture(matrices, x, y, MENU_U_OFFSET, MENU_V_OFFSET, WIDTH, HEIGHT);

            for (PowerGroup group : groups) {
                group.render(matrices, mouseX, mouseY, delta);

                if (group.isToggled()) {
                    group.buttons.forEach((button) -> button.render(matrices, mouseX, mouseY, delta));
                }
            }

            RenderSystem.popMatrix();
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isOpen()) {
            for (PowerGroup group : groups) {
                if (group.mouseClicked(mouseX, mouseY, button)) {
                    if (group.index != focusedGroup) {
                        group.setToggled(true);
                        groups.get(focusedGroup).setToggled(false);
                        focusedGroup = group.index;
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public void toggleOpen() {
        open = !open;
    }

    public boolean isOpen() {
        return open;
    }
}
