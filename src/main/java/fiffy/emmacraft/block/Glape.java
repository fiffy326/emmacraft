package fiffy.emmacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Glape extends Block {
    public Glape(Settings settings) {
        super(settings);
    }

    /**
     * Gets the PlayerEntity closest to a position within a radius in the World object, if one exists.
     *
     * @param world  World object being searched
     * @param pos    Center of the search area
     * @param radius Search radius
     * @return PlayerEntity or null
     */
    private static PlayerEntity getClosestPlayer(@NotNull World world, @NotNull BlockPos pos, int radius) {
        return world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), radius, false);
    }

    /**
     * Gets the PlayerEntity closest to a position within a radius in the WorldAccess object, if one exists.
     *
     * @param world  WorldAccess object being searched
     * @param pos    Center of the search area
     * @param radius Search radius
     * @return PlayerEntity or null
     */
    private static PlayerEntity getClosestPlayer(WorldAccess world, BlockPos pos, int radius) {
        return getClosestPlayer((World) world, pos, radius);
    }

    /**
     * Plays a sound originating from a position in the World object.
     *
     * @param world World object to play the sound in
     * @param pos   Sound origin point
     * @param sound Sound event to play
     */
    private static void playSound(@NotNull World world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    /**
     * Plays a sound originating from a position in the WorldAccess object.
     *
     * @param world WorldAccess object to play the sound in
     * @param pos   Sound origin point
     * @param sound Sound event to play
     */
    private static void playSound(@NotNull WorldAccess world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    protected ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            playSound(world, pos, SoundEvents.ENTITY_CAT_PURREOW);
            player.sendMessage(Text.translatable("block.emmacraft.glape.text.used"));
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            playSound(world, pos, SoundEvents.ENTITY_CAT_PURREOW);
            if (placer instanceof PlayerEntity player) {
                player.sendMessage(Text.translatable("block.emmacraft.glape.text.placed"));
            }
        }
    }

    @Override
    protected void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);
        if (!world.isClient) {
            playSound(world, pos, SoundEvents.ENTITY_CAT_HURT);
            player.sendMessage(Text.translatable("block.emmacraft.glape.text.damaged"));
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (!world.isClient()) {
            playSound(world, pos, SoundEvents.ENTITY_CAT_DEATH);

            PlayerEntity player = getClosestPlayer(world, pos, 8);
            if (player != null) {
                player.sendMessage(Text.translatable("block.emmacraft.glape.text.broken"));
            }
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        super.onDestroyedByExplosion(world, pos, explosion);
        if (!world.isClient()) {
            playSound(world, pos, SoundEvents.ENTITY_CAT_DEATH);
            PlayerEntity closestPlayer = getClosestPlayer(world, pos, 32);
            if (closestPlayer != null) {
                closestPlayer.sendMessage(Text.translatable("block.emmacraft.glape.text.blownUp"));
            }
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance);

        if (!world.isClient()) {
            if (entity instanceof LivingEntity) {
                playSound(world, pos, SoundEvents.ENTITY_CAT_HURT);
                if (entity instanceof PlayerEntity player) {
                    player.sendMessage(Text.translatable("block.emmacraft.glape.text.landedOn"));
                }
            }
        }
    }
}
