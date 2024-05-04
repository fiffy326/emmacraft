package fiffy.emmacraft.block;

import fiffy.emmacraft.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

    @Override
    protected ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            Utils.playSound(world, pos, SoundEvents.ENTITY_CAT_PURREOW);
            player.sendMessage(Text.translatable("block.emmacraft.glape.text.used"));
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            Utils.playSound(world, pos, SoundEvents.ENTITY_CAT_PURREOW);
            if (placer instanceof PlayerEntity player) {
                player.sendMessage(Text.translatable("block.emmacraft.glape.text.placed"));
            }
        }
    }

    @Override
    protected void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);
        if (!world.isClient) {
            Utils.playSound(world, pos, SoundEvents.ENTITY_CAT_HURT);
            player.sendMessage(Text.translatable("block.emmacraft.glape.text.damaged"));
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (!world.isClient()) {
            Utils.playSound(world, pos, SoundEvents.ENTITY_CAT_DEATH);

            PlayerEntity player = Utils.getClosestPlayer(world, pos, 8);
            if (player != null) {
                player.sendMessage(Text.translatable("block.emmacraft.glape.text.broken"));
            }
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        super.onDestroyedByExplosion(world, pos, explosion);
        if (!world.isClient()) {
            Utils.playSound(world, pos, SoundEvents.ENTITY_CAT_DEATH);
            PlayerEntity closestPlayer = Utils.getClosestPlayer(world, pos, 32);
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
                Utils.playSound(world, pos, SoundEvents.ENTITY_CAT_HURT);
                if (entity instanceof PlayerEntity player) {
                    player.sendMessage(Text.translatable("block.emmacraft.glape.text.landedOn"));
                }
            }
        }
    }
}
