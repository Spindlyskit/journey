package me.spindlyskit.journey.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PowersMenuWidget extends DrawableHelper implements Drawable {
    protected static final Identifier TEXTURE = new Identifier("journey", "textures/gui/powers_menu.png");
    public static final int WIDTH = 46;
    public static final int HEIGHT = 147;
    private static final int INVENTORY_HEIGHT = 166;
    protected MinecraftClient client;
    private int parentWidth;
    private int parentHeight;
    private boolean open = false;

    public void initialize(int parentWidth, int parentHeight, MinecraftClient client) {
        this.client = client;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (open) {
            RenderSystem.pushMatrix();
            RenderSystem.translatef(0.0f, 0.0f, 100.0f);
            this.client.getTextureManager().bindTexture(TEXTURE);
            int x = (this.parentWidth) / 2 - 90;
            int y = (this.parentHeight - HEIGHT - (INVENTORY_HEIGHT - HEIGHT)) / 2;
            drawTexture(matrices, x, y, 1, 1, WIDTH, HEIGHT);
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
