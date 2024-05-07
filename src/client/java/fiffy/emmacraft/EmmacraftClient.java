package fiffy.emmacraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import static fiffy.emmacraft.Emmacraft.LOGGER;

public class EmmacraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing Emmacraft client");

		LOGGER.info("Adding '/deathcoords' to the command registry.");
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("deathcoords").executes(context -> {
				String deathCoords;
				PlayerEntity player = context.getSource().getPlayer();
				if (player != null) {
					if (player.getLastDeathPos().isPresent()) {
						BlockPos pos = player.getLastDeathPos().get().pos();
						int x = pos.getX();
						int y = pos.getY();
						int z = pos.getZ();
						deathCoords = String.format("Death coords: (%d, %d, %d)", x, y, z);
					} else {
						deathCoords = "You haven't died yet.";
					}
				} else {
					deathCoords = "You haven't died yet.";
				}
                context.getSource().sendFeedback(Text.literal(deathCoords));
                return 1;
            }));
		});
	}
}