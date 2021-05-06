package me.spindlyskit.journey.mixin;

import me.spindlyskit.journey.ui.PowersMenuButton;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> {
	private PowersMenuButton powersMenuButton;

	public InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
		super(screenHandler, playerInventory, text);
	}

	@Inject(at = @At("TAIL"), method = "init()V")
	private void init(CallbackInfo ci) {
			powersMenuButton = addButton(new PowersMenuButton(x, height / 2, (buttonWidget) -> {
				((PowersMenuButton) buttonWidget).resetPos(x, height / 2);
				System.out.println("Button Pressed!");
			}));
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
	}
}
