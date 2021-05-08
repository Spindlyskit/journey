package me.spindlyskit.journey;

import me.spindlyskit.journey.network.ServerPacketRegistry;
import net.fabricmc.api.ModInitializer;

public class JourneyMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Initialize client -> server packets
        ServerPacketRegistry.init();
    }
}
