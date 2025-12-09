package net.malkxp.added_origins;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.malkxp.added_origins.particle.ModParticles;
import net.malkxp.added_origins.particle.XestrialSnowParticle;

public class AddedOriginsRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.XESTRIAL_SNOW, XestrialSnowParticle.Factory::new);
    }
}
