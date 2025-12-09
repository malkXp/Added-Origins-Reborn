package net.malkxp.added_origins.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.malkxp.added_origins.AddedOriginsReborn;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType XESTRIAL_SNOW = registerParticle("xestrial_snow", FabricParticleTypes.simple());

    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(AddedOriginsReborn.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        AddedOriginsReborn.LOGGER.info("Registering Added Origins Particles");
    }
}
