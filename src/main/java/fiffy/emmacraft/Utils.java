package fiffy.emmacraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;

public class Utils {
    /**
     * Gets the PlayerEntity closest to a position within a radius in the World object, if one exists.
     * @param world World object being searched
     * @param pos Center of the search area
     * @param radius Search radius
     * @return PlayerEntity or null
     */
    public static PlayerEntity getClosestPlayer(@NotNull World world, @NotNull BlockPos pos, int radius) {
        return world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), radius, false);
    }

    /**
     * Gets the PlayerEntity closest to a position within a radius in the WorldAccess object, if one exists.
     * @param world WorldAccess object being searched
     * @param pos Center of the search area
     * @param radius Search radius
     * @return PlayerEntity or null
     */
    public static PlayerEntity getClosestPlayer(WorldAccess world, BlockPos pos, int radius) {
        return getClosestPlayer((World) world, pos, radius);
    }

    /**
     * Plays a sound originating from a position in the World object.
     * @param world World object to play the sound in
     * @param pos Sound origin point
     * @param sound Sound event to play
     */
    public static void playSound(@NotNull World world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    /**
     * Plays a sound originating from a position in the WorldAccess object.
     * @param world WorldAccess object to play the sound in
     * @param pos Sound origin point
     * @param sound Sound event to play
     */
    public static void playSound(@NotNull WorldAccess world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
