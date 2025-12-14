package net.malkxp.added_origins.init;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.malkxp.added_origins.power.*;
import net.malkxp.added_origins.AddedOriginsReborn;
import net.malkxp.added_origins.power.PowderSnowImmune;
import net.minecraft.registry.Registry;

public class ModPowers {
    public static final PowerFactory<?> POWDER_SNOW_IMMUNE = new PowerFactory<>(AddedOriginsReborn.id("powder_snow_immune"), new SerializableData(), data -> (type, entity) -> new PowderSnowImmune(type, entity)).allowCondition();
    public static final PowerFactory<?> FREEZE_IMMUNE = new PowerFactory<>(AddedOriginsReborn.id("freeze_immune"), new SerializableData(), data -> (type, entity) -> new FreezeImmune(type, entity)).allowCondition();
    public static final PowerFactory<?> FIRE_RENDERING_IMMUNE = new PowerFactory<>(AddedOriginsReborn.id("fire_rendering_immune"), new SerializableData(), data -> (type, entity) -> new FireRenderingImmune(type, entity)).allowCondition();
    public static final PowerFactory<?> MOB_VISION_IMMUNE = new PowerFactory<>(AddedOriginsReborn.id("mob_vision_avoid"), new SerializableData().add("mob_condition", ApoliDataTypes.ENTITY_CONDITION), data -> (type, livingEntity) -> new MobVisionImmunity(type, livingEntity, data.get("mob_condition"))).allowCondition();
    //public static final PowerFactory<?> LAVA_FALLING_IMMUNE = new PowerFactory<>(AddedOriginsReborn.id("like_lava"), new SerializableData(), data -> (type, entity) -> new LavaFallingImmune(type, entity)).allowCondition();


    //public static final PowerFactory<?> FIRE_OVERLAY_CONTROL = new PowerFactory<>(AddedOriginsReborn.id("fire_overlay_control"), new SerializableData(), data -> (type, entity) -> new PowderSnowImmune(type, entity)).allowCondition();

    public static void registerPowers() {
        Registry.register(ApoliRegistries.POWER_FACTORY, POWDER_SNOW_IMMUNE.getSerializerId(), POWDER_SNOW_IMMUNE);
        Registry.register(ApoliRegistries.POWER_FACTORY, FREEZE_IMMUNE.getSerializerId(), FREEZE_IMMUNE);
        Registry.register(ApoliRegistries.POWER_FACTORY, FIRE_RENDERING_IMMUNE.getSerializerId(), FIRE_RENDERING_IMMUNE);
        Registry.register(ApoliRegistries.POWER_FACTORY, MOB_VISION_IMMUNE.getSerializerId(), MOB_VISION_IMMUNE);
        //Registry.register(ApoliRegistries.POWER_FACTORY, LAVA_FALLING_IMMUNE.getSerializerId(), LAVA_FALLING_IMMUNE);
        //Registry.register(ApoliRegistries.POWER_FACTORY, FIRE_OVERLAY_CONTROL.getSerializerId(), FIRE_OVERLAY_CONTROL);
    }
}