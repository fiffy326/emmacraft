package fiffy.emmacraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class Scroll extends Item {
    public Scroll(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0f, 1.0f);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
