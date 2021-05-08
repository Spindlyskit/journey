package me.spindlyskit.journey.mixin;

import me.spindlyskit.journey.access.PlayerEntityAccess;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityAccess {
    private final PowersMenuOptions powersMenuOptions = new PowersMenuOptions();

    private PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "writeCustomDataToTag")
    private void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.put("PowersMenu", powersMenuOptions.serialize(new CompoundTag()));
    }

    @Inject(at = @At("TAIL"), method = "readCustomDataFromTag")
    private void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        powersMenuOptions.deserialize(tag.getCompound("PowersMenu"));
    }

    @Override
    public PowersMenuOptions getPowersMenuOptions() {
        return powersMenuOptions;
    }
}
