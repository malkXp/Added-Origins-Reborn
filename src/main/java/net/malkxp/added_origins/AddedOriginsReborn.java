package net.malkxp.added_origins;

import net.fabricmc.api.ModInitializer;

import net.malkxp.added_origins.init.ModPowers;
import net.malkxp.added_origins.particle.ModParticles;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddedOriginsReborn implements ModInitializer {
	public static final String MOD_ID = "added_origins";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModParticles.registerParticles();
		ModPowers.registerPowers();
	}

	public static Identifier id(String value) {
		return new Identifier(MOD_ID, value);
	}
}