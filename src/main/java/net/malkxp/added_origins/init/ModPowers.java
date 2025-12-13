package net.malkxp.added_origins.init;

import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.malkxp.added_origins.power.*;
import net.malkxp.added_origins.AddedOriginsReborn;
import net.malkxp.added_origins.power.PowderSnowImmune;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;

import java.util.Collections;

public class ModPowers {
    public static final PowerFactory<?> POWDER_SNOW_IMMUNE = new PowerFactory<>(AddedOriginsReborn.id("powder_snow_immune"), new SerializableData(), data -> (type, entity) -> new PowderSnowImmune(type, entity)).allowCondition();
    public static final PowerFactory<?> FREEZE_IMMUNE = new PowerFactory<>(AddedOriginsReborn.id("freeze_immune"), new SerializableData(), data -> (type, entity) -> new FreezeImmune(type, entity)).allowCondition();
    //public static final PowerFactory<?> FIRE_OVERLAY_CONTROL = new PowerFactory<>(AddedOriginsReborn.id("fire_overlay_control"), new SerializableData(), data -> (type, entity) -> new PowderSnowImmune(type, entity)).allowCondition();

    public static void registerPowers() {
        Registry.register(ApoliRegistries.POWER_FACTORY, POWDER_SNOW_IMMUNE.getSerializerId(), POWDER_SNOW_IMMUNE);
        Registry.register(ApoliRegistries.POWER_FACTORY, FREEZE_IMMUNE.getSerializerId(), FREEZE_IMMUNE);
        //Registry.register(ApoliRegistries.POWER_FACTORY, FIRE_OVERLAY_CONTROL.getSerializerId(), FIRE_OVERLAY_CONTROL);
    }
}