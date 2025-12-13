package net.malkxp.added_origins.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;

public class FreezeImmune extends Power {
    public FreezeImmune(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }
}