package net.malkxp.added_origins.mixin.powdersnowimmune;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.malkxp.added_origins.power.FreezeImmune;
import net.malkxp.added_origins.power.PowderSnowImmune;
import net.minecraft.block.BlockState;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void added_origins$canStandOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        for (PowderSnowImmune canStandOnPowderSnowPower : PowerHolderComponent.getPowers(entity, PowderSnowImmune.class)) {
            if (canStandOnPowderSnowPower.isActive()) {
                cir.setReturnValue(true);
                return;
            }
        }
    }

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private static void added_origins$onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        for (FreezeImmune canFreezePower : PowerHolderComponent.getPowers(entity, FreezeImmune.class)) {
            if (canFreezePower.isActive()) {
                ci.cancel();
                return;
            }
        }
    }
}

//Credits to MoriyaShiine