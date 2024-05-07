package fiffy.emmacraft;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import static fiffy.emmacraft.Emmacraft.LOGGER;

public abstract class Commands {
    /**
     * Registers custom commands.
     */
    public static void register() {
        registerLastDeath();
    }

    /**
     * Registers the '/lastdeath' command, which prints the location of your most recent death.
     */
    private static void registerLastDeath() {
        LOGGER.info("Adding '/lastdeath' to the command registry.");
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("lastdeath").executes(context -> {
                ClientPlayerEntity player = context.getSource().getPlayer();
                String lastDeathCoords;
                if (player != null && player.getLastDeathPos().isPresent()) {
                    BlockPos pos = player.getLastDeathPos().get().pos();
                    lastDeathCoords = String.format("Last death: (%d, %d, %d)", pos.getX(), pos.getY(), pos.getZ());
                } else {
                    lastDeathCoords = "You haven't died yet.";
                }
                context.getSource().sendFeedback(Text.literal(lastDeathCoords));
                return 1;
            }));
        });
    }
}