package me.spindlyskit.journey.mixin;

import me.spindlyskit.journey.ui.PowersMenuButton;
import me.spindlyskit.journey.ui.PowersMenuWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> {
    @Shadow @Final private RecipeBookWidget recipeBook;
    @Shadow private boolean narrow;
    @Shadow private boolean mouseDown;
    private PowersMenuButton powersMenuButton;
    private TexturedButtonWidget recipeBookButton;
    private final PowersMenuWidget powersMenuWidget = new PowersMenuWidget();

    public InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(at = @At("TAIL"), method = "init()V")
    private void init(CallbackInfo ci) {
        powersMenuWidget.initialize(this.width, this.height, this.client);

        powersMenuButton = addButton(new PowersMenuButton(x, height / 2, (buttonWidget) -> {
            if (recipeBook.isOpen()) {
                recipeBook.reset(narrow);
                recipeBook.toggleOpen();
                x = recipeBook.findLeftEdge(this.narrow, this.width, this.backgroundWidth);
                mouseDown = true;
                resetRecipeBookButtonPosition();
            }

            powersMenuWidget.toggleOpen();
            ((PowersMenuButton) buttonWidget).resetPos(x, height / 2);
        }));
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawMouseoverTooltip(Lnet/minecraft/client/util/math/MatrixStack;II)V"), method = "render")
    private void renderPowersMenu(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        powersMenuWidget.render(matrices, mouseX, mouseY, delta);
    }

    @SuppressWarnings("UnresolvedMixinReference")
    // This hooks into the synthetic function called when the recipe button is pressed
    // It uses the method name from the bytecode and will require updating each minecraft version
    // There is probably a better approach here
    @Inject(at = @At("TAIL"), method = "method_19891(Lnet/minecraft/client/gui/widget/ButtonWidget;)V")
    private void onRecipeBookOpen(ButtonWidget b, CallbackInfo ci) {
        if (powersMenuButton != null) {
            powersMenuButton.resetPos(x, height / 2);
        }

        if (powersMenuWidget.isOpen()) {
            powersMenuWidget.toggleOpen();
        }
    }

    @Redirect(at = @At(value = "INVOKE", target = "net/minecraft/client/gui/screen/ingame/InventoryScreen.addButton(Lnet/minecraft/client/gui/widget/AbstractButtonWidget;)Lnet/minecraft/client/gui/widget/AbstractButtonWidget;"), method = "init()V")
    private AbstractButtonWidget captureRecipeButton(InventoryScreen inventoryScreen, AbstractButtonWidget button) {
        recipeBookButton = (TexturedButtonWidget) button;
        return addButton(button);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/recipebook/RecipeBookWidget;isOpen()Z"), method = "render")
    private boolean dontDrawStatuesWhenWidgetOpen(RecipeBookWidget recipeBookWidget) {
        return powersMenuWidget.isOpen() || recipeBookWidget.isOpen();
    }

    private void resetRecipeBookButtonPosition() {
        if (recipeBookButton != null) {
            // Offsets are taken from minecraft's source
            recipeBookButton.setPos(this.x + 104, this.height / 2 - 22);
        }
    }
}
