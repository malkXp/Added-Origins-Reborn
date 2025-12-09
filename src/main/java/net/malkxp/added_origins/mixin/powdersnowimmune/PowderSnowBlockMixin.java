package net.malkxp.added_origins.mixin.powdersnowimmune;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.malkxp.added_origins.power.PowderSnowImmune;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void extraorigins$canStandOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        for (PowderSnowImmune canStandOnPowderSnowPower : PowerHolderComponent.getPowers(entity, PowderSnowImmune.class)) {
            if (canStandOnPowderSnowPower.isActive()) {
                cir.setReturnValue(true);
                return;
            }
        }
    }
}