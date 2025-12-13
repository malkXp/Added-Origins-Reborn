package net.malkxp.added_origins.mixin.overlayrenderer;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.malkxp.added_origins.power.FireRenderingImmune;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameOverlayRenderer.class)

public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void added_origins$preventFireOverlay(MinecraftClient client, MatrixStack matrixStack, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        for (FireRenderingImmune canNotFireRender : PowerHolderComponent.getPowers(minecraftClient.cameraEntity, FireRenderingImmune.class)) {
            if (canNotFireRender.isActive()) {
                ci.cancel();
                return;
            }
        }
    }
}
