package fiffy.emmacraft;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;
import static fiffy.emmacraft.Emmacraft.LOGGER;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public abstract class Commands {
    public static void register() {
        registerCoords();
        registerDeathCoords();
        registerPortalCoords();
    }

    /**
     * Registers the '/coords' command, which sends your current coordinates in chat.
     */
    private static void registerCoords() {
        LOGGER.info("Adding '/coords' to the command registry");
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("coords").executes(context -> {
                String coords;
                ClientPlayerEntity player = context.getSource().getPlayer();
                ClientPlayNetworkHandler networkHandler = MinecraftClient.getInstance().getNetworkHandler();
                if (networkHandler != null && player != null && player.getBlockPos() != null) {
                    BlockPos pos = player.getBlockPos();
                    coords = String.format("Coordinates: (%d, %d, %d)", pos.getX(), pos.getY(), pos.getZ());
                    networkHandler.sendChatMessage(coords);
                }
                return SINGLE_SUCCESS;
            }));
        });
    }

    /**
     * Registers the '/deathcoords' command, which prints the coordinates of your most recent death.
     */
    private static void registerDeathCoords() {
        LOGGER.info("Adding '/deathcoords' to the command registry");
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("deathcoords").executes(context -> {
                String coords;
                ClientPlayerEntity player = context.getSource().getPlayer();
                if (player != null && player.getLastDeathPos().isPresent()) {
                    BlockPos pos = player.getLastDeathPos().get().pos();
                    coords = String.format("Death coordinates: (%d, %d, %d)", pos.getX(), pos.getY(), pos.getZ());
                    context.getSource().sendFeedback(Text.literal(coords));
                }
                return SINGLE_SUCCESS;
            }));
        });
    }

    /**
     * Registers the '/portalcoords' command, which converts nether coordinates for portal linking.
     */
    private static void registerPortalCoords() {
        LOGGER.info("Adding '/portalcoords' to the command registry");
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("portalcoords").executes(context -> {
                ClientPlayerEntity player = context.getSource().getPlayer();
                if (player != null && player.getPos() != null) {
                    Vec3d pos = player.getPos();
                    if (isInOverworld(player)) {
                        long x = Math.round(pos.x / 8);
                        long y = Math.round(pos.y);
                        long z = Math.round(pos.z / 8);
                        String coords = String.format("Nether coordinates: (%d, %d, %d)", x, y, z);
                        context.getSource().sendFeedback(Text.literal(coords));
                    } else if (isInNether(player)) {
                        long x = Math.round(pos.x * 8);
                        long y = Math.round(pos.y);
                        long z = Math.round(pos.z * 8);
                        String coords = String.format("Overworld coordinates: (%d, %d, %d)", x, y, z);
                        context.getSource().sendFeedback(Text.literal(coords));
                    }
                }
                return SINGLE_SUCCESS;
            }));
        });
    }

    /**
     * Determines whether a player is in the nether.
     *
     * @param player The player object.
     * @return True if the player is in the nether.
     */
    private static boolean isInNether(@NotNull ClientPlayerEntity player) {
        return (player.getWorld().getRegistryKey() == World.NETHER);
    }

    /**
     * Determines whether a player is in the overworld.
     *
     * @param player The player object.
     * @return True if the player is in the overworld.
     */
    private static boolean isInOverworld(@NotNull ClientPlayerEntity player) {
        return (player.getWorld().getRegistryKey() == World.OVERWORLD);
    }

    /**
     * Determines whether a player is in the end.
     *
     * @param player The player object.
     * @return True if the player is in the end.
     */
    private static boolean isInEnd(@NotNull ClientPlayerEntity player) {
        return (player.getWorld().getRegistryKey() == World.END);
    }
}