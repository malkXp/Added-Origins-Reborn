package net.malkxp.added_origins.mixin.powdersnowimmune;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.malkxp.added_origins.power.FreezeImmune;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.malkxp.added_origins.util.FogData;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void added_origins$canViewInPowderSnow(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        Entity entity = camera.getFocusedEntity();
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        FogData fogData = new FogData(fogType);
        for (FreezeImmune canFreezePower : PowerHolderComponent.getPowers(entity, FreezeImmune.class)) {
            if (canFreezePower.isActive()) {
                if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
                    ci.cancel();
                    if (entity.isSpectator()) {
                        fogData.fogStart = -8.0F;
                        fogData.fogEnd = viewDistance * 0.5F;
                    } else {
                        fogData.fogStart = 0.0F;
                        fogData.fogEnd = 8.0F;
                    }
                    RenderSystem.setShaderFogStart(fogData.fogStart);
                    RenderSystem.setShaderFogEnd(fogData.fogEnd);
                    RenderSystem.setShaderFogShape(fogData.fogShape);
                }
                return;
            }
        }
    }
}