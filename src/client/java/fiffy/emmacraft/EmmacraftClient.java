package fiffy.emmacraft;

import net.fabricmc.api.ClientModInitializer;

import static fiffy.emmacraft.Emmacraft.LOGGER;

public class EmmacraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Emmacraft client");
        Commands.register();
    }
}