package me.spindlyskit.journey.mixin;

import com.mojang.authlib.GameProfile;
import me.spindlyskit.journey.access.PlayerEntityAccess;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements PlayerEntityAccess {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
    private void cancelDamageInGodMode(DamageSource damageSource, CallbackInfoReturnable<Boolean> ci) {
        if (getPowersMenuOptions().isGodmode()) {
            ci.setReturnValue(damageSource != DamageSource.OUT_OF_WORLD);
        }
    }
}
