package net.malkxp.added_origins.mixin.changefogeffectiveness;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.malkxp.added_origins.power.FogIntensity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.malkxp.added_origins.util.FogData;

@Mixin(BackgroundRenderer.class)
public abstract class SeparateBackgroundRendererMixin {
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void added_origins$canViewInPowderSnow(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        Entity entity = camera.getFocusedEntity();
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        FogData fogData = new FogData(fogType);
        for (FogIntensity intenseFog : PowerHolderComponent.getPowers(entity, FogIntensity.class)) {
            if (intenseFog.isActive()) {
                CameraSubmersionType trueSubmersionType = intenseFog.cameraFogType();

                float startFog = intenseFog.mainStart();
                float endFog = intenseFog.mainEnd();
                if (cameraSubmersionType == trueSubmersionType) {
                    if (trueSubmersionType == CameraSubmersionType.LAVA) {
                        ci.cancel();
                        if (entity.isSpectator()) {
                            fogData.fogStart = -8.0F;
                            fogData.fogEnd = viewDistance * 0.5F;
                        } else if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                            fogData.fogStart = startFog - 0.25F;
                            fogData.fogEnd = endFog * 3;
                        } else {
                            fogData.fogStart = startFog;
                            fogData.fogEnd = endFog;
                        }
                        RenderSystem.setShaderFogStart(fogData.fogStart);
                        RenderSystem.setShaderFogEnd(fogData.fogEnd);
                        RenderSystem.setShaderFogShape(fogData.fogShape);
                    } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
                        ci.cancel();
                        if (entity.isSpectator()) {
                            fogData.fogStart = -8.0F;
                            fogData.fogEnd = viewDistance * 0.5F;
                        } else {
                            fogData.fogStart = startFog;
                            fogData.fogEnd = endFog;
                        }
                        RenderSystem.setShaderFogStart(fogData.fogStart);
                        RenderSystem.setShaderFogEnd(fogData.fogEnd);
                        RenderSystem.setShaderFogShape(fogData.fogShape);
                    } else if (cameraSubmersionType == CameraSubmersionType.WATER) {
                        ci.cancel();
                        fogData.fogStart = startFog;
                        fogData.fogEnd = endFog;
                        if (entity instanceof ClientPlayerEntity clientPlayerEntity) {
                            fogData.fogEnd = fogData.fogEnd * Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                            RegistryEntry<Biome> registryEntry = clientPlayerEntity.getWorld().getBiome(clientPlayerEntity.getBlockPos());
                            if (registryEntry.isIn(BiomeTags.HAS_CLOSER_WATER_FOG)) {
                                fogData.fogEnd *= 0.85F;
                            }
                        }

                        if (fogData.fogEnd > viewDistance) {
                            fogData.fogEnd = viewDistance;
                            fogData.fogShape = FogShape.CYLINDER;
                        }
                        RenderSystem.setShaderFogStart(fogData.fogStart);
                        RenderSystem.setShaderFogEnd(fogData.fogEnd);
                        RenderSystem.setShaderFogShape(fogData.fogShape);
                    }
                }
                return;
            }
        }
    }
}