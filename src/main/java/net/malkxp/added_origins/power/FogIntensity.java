package net.malkxp.added_origins.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.LivingEntity;

public class FogIntensity extends Power {
    private final Float fogMainStart;
    private final Float fogMainEnd;
    private final CameraSubmersionType fogMainTag;

    public FogIntensity(PowerType<?> type, LivingEntity entity, Float fogStart, Float fogEnd, CameraSubmersionType fogTag) {
        super(type, entity);
        this.fogMainStart = fogStart;
        this.fogMainEnd = fogEnd;
        this.fogMainTag = fogTag;
    }

    public float mainStart() {
        return this.fogMainStart;
    }

    public float mainEnd() {
        return this.fogMainEnd;
    }

    public CameraSubmersionType cameraFogType() {
        return this.fogMainTag;
    }
}