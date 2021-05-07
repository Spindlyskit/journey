package me.spindlyskit.journey.ui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
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
    private final List<PowersMenuGroup> groups = Lists.newArrayList();

    public void initialize(int parentWidth, int parentHeight, MinecraftClient client) {
        this.client = client;
        x = (parentWidth) / 2 - 90;
        y = (parentHeight - HEIGHT - (INVENTORY_HEIGHT - HEIGHT)) / 2;

        int baseX = x - PowersMenuGroup.WIDTH + 5;
        int baseY = y + 3;
        for (int i = 0; i < 3; i++) {
            this.groups.add(new PowersMenuGroup(baseX, baseY, i, i == 0));
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (open) {
            RenderSystem.pushMatrix();
            RenderSystem.translatef(0.0f, 0.0f, 100.0f);
            this.client.getTextureManager().bindTexture(TEXTURE);

            drawTexture(matrices, x, y, MENU_U_OFFSET, MENU_V_OFFSET, WIDTH, HEIGHT);

            for (PowersMenuGroup group : groups) {
                group.render(matrices, mouseX, mouseY, delta);
            }

            RenderSystem.popMatrix();
        }
    }

    public void toggleOpen() {
        open = !open;
    }

    public boolean isOpen() {
        return open;
    }
}
