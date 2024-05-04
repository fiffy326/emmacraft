package fiffy.emmacraft;

import static fiffy.emmacraft.Emmacraft.LOGGER;
import net.fabricmc.api.ClientModInitializer;

public class EmmacraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing Emmacraft client");
	}
}