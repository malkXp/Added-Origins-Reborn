package net.malkxp.added_origins.util;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.FogShape;

public class FogData {
    public final BackgroundRenderer.FogType fogType;
    public float fogStart;
    public float fogEnd;
    public FogShape fogShape = FogShape.SPHERE;

    public FogData(BackgroundRenderer.FogType fogType) {
        this.fogType = fogType;
    }
}
