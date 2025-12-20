package net.malkxp.added_origins.mixin.powdersnowimmune;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.malkxp.added_origins.power.FreezeImmune;
import net.malkxp.added_origins.power.PowderSnowImmune;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class PowderSnowCameraMixin {
    @Shadow public abstract Entity getFocusedEntity();
    @Shadow public abstract BlockPos getBlockPos();
    @Shadow private BlockView area;

    @Inject(method = "getSubmersionType", at = @At("HEAD"), cancellable = true)
    private void added_origins$canViewInPowderSnow(CallbackInfoReturnable<CameraSubmersionType> cir) {
        Entity entity = this.getFocusedEntity();
        for (FreezeImmune canFreezePower : PowerHolderComponent.getPowers(entity, FreezeImmune.class)) {
            if (canFreezePower.isActive()) {
                BlockState blockState = this.area.getBlockState(getBlockPos());
                if (blockState.isOf(Blocks.POWDER_SNOW)) {
                    //cir.cancel();
                }
                return;
            }
        }
    }
}
