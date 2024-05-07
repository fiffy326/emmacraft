package fiffy.emmacraft;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Emmacraft implements ModInitializer {
    public static final String MOD_ID = "emmacraft";
    public static final String MOD_NAME = "Emmacraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Emmacraft");
        ItemGroups.register();
        Items.register();
        Blocks.register();
    }
}