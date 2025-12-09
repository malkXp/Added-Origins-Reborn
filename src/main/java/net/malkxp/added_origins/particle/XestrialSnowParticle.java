package net.malkxp.added_origins.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;

public class XestrialSnowParticle extends SpriteBillboardParticle {
    public XestrialSnowParticle(ClientWorld clientWorld, double x, double y, double z,
                                SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);

        this.setSprite(spriteProvider.getSprite(this.random.nextInt(5), 5));
        this.gravityStrength = 0.065F;
        this.maxAge = 50;
        float f = this.random.nextBoolean() ? 0.025F : 0.035F;
        this.scale = f;
        this.velocityX = 0.0125F;
        this.velocityY = 0;
        this.velocityZ = 0.025F;
        this.setBoundingBoxSpacing(f, f);
        this.velocityMultiplier = .975F;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z,
                                                 double velocityX, double velocityY, double velocityZ) {
            return new XestrialSnowParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
