package net.malkxp.added_origins.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import java.util.HashSet;
import java.util.function.Predicate;

public class MobVisionImmunity extends Power {
    private final HashSet<EntityType<?>> mobTypes = new HashSet<>();
    private Predicate<LivingEntity> mobCondition;

    public MobVisionImmunity(PowerType<?> type, LivingEntity entity, Predicate<LivingEntity> mobCondition) {
        super(type, entity);
        this.mobCondition = mobCondition;
    }

    public boolean shouldIgnore(LivingEntity mob) {
        return this.mobCondition.test(mob);
    }
}