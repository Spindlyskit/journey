package me.spindlyskit.journey.mixin;

import me.spindlyskit.journey.ui.powersmenu.PowersMenuButton;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuWidget;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        if (!recipeBook.isOpen()) {
            x = findWidgetLeftEdge();
            resetRecipeBookButtonPosition();
        }
        powersMenuWidget.initialize(this.width, this.height, this.client);

        powersMenuButton = addButton(new PowersMenuButton(x, height / 2, (buttonWidget) -> {
            if (recipeBook.isOpen()) {
                recipeBook.toggleOpen();
            }

            powersMenuWidget.toggleOpen();
            x = findWidgetLeftEdge();
            ((PowersMenuButton) buttonWidget).resetPos(x, height / 2);
            resetRecipeBookButtonPosition();
            recipeBook.reset(narrow);
            mouseDown = true;
        }));
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawMouseoverTooltip(Lnet/minecraft/client/util/math/MatrixStack;II)V"), method = "render")
    private void renderPowersMenu(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        powersMenuWidget.render(matrices, mouseX, mouseY, delta);
    }

    /**
     * Close the powers menu widget and move the button when the recipe book is opened
     */
    // This hooks into the synthetic function called when the recipe button is pressed
    // It uses the method name from the bytecode and will require updating each minecraft version
    // There is probably a better approach here
    @SuppressWarnings("UnresolvedMixinReference")
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

    /**
     * Used to decided whether to draw status effects to the left of the inventory
     */
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/recipebook/RecipeBookWidget;isOpen()Z"), method = "render")
    private boolean dontDrawStatusesWhenWidgetOpen(RecipeBookWidget recipeBookWidget) {
        return powersMenuWidget.isOpen() || recipeBookWidget.isOpen();
    }

    /**
     * Handle clicking on powers menu
     */
    @Inject(at = @At("TAIL"), method = "mouseClicked", cancellable = true)
    private void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> ci) {
        if (powersMenuWidget.isOpen() && powersMenuWidget.mouseClicked(mouseX, mouseY, button)) {
            ci.setReturnValue(true);
        }
    }

    protected int findWidgetLeftEdge() {
        if (powersMenuWidget.isOpen()) {
            return PowersMenuWidget.WIDTH + (width - backgroundWidth) / 2;
        }

        return recipeBook.findLeftEdge(narrow, width, backgroundWidth);
    }

    private void resetRecipeBookButtonPosition() {
        if (recipeBookButton != null) {
            // Offsets are taken from minecraft's source
            recipeBookButton.setPos(this.x + 104, this.height / 2 - 22);
        }
    }
}
