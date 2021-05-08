package me.spindlyskit.journey;

import me.spindlyskit.journey.network.ClientPacketRegistry;
import net.fabricmc.api.ClientModInitializer;

public class JourneyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPacketRegistry.init();
    }
}
