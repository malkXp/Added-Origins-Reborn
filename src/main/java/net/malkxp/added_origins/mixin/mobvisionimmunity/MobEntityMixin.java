package net.malkxp.added_origins.mixin.mobvisionimmunity;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.malkxp.added_origins.power.MobVisionImmunity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> type, World level) {
        super(type, level);
        throw new AssertionError("MobMixin constructor called.");
    }

    @ModifyVariable(method = "setTarget", at = @At("HEAD"))
    private LivingEntity modifyTarget(LivingEntity target) {
        if (!(target instanceof PlayerEntity)) {
            return target;
        }

        //boolean shouldIgnore = Boolean.parseBoolean(null);
        for (MobVisionImmunity canAvoidMobs : PowerHolderComponent.getPowers(target, MobVisionImmunity.class)) {
            if (canAvoidMobs.isActive()) {
                boolean shouldIgnore = canAvoidMobs.shouldIgnore(this);
                return shouldIgnore ? null : target;
            }
        }
        return target;
    }
}