package fiffy.emmacraft.mixin.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.GlobalPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Optional;

@Mixin(PlayerEntity.class)
public abstract class LastDeathCoordsMixin {
    @Shadow public abstract Optional<GlobalPos> getLastDeathPos();

    @Shadow public abstract void sendMessage(Text message, boolean overlay);

    @Inject(method = "setLastDeathPos", at = @At("TAIL"))
    private void init(CallbackInfo info) {
        if (this.getLastDeathPos().isPresent()) {
            int x = this.getLastDeathPos().get().pos().getX();
            int y = this.getLastDeathPos().get().pos().getY();
            int z = this.getLastDeathPos().get().pos().getZ();
            String lastDeathCoords = "Death: (" + x + ", " + y + ", " + z + ")";
            this.sendMessage(Text.of(lastDeathCoords), false);
        }
    }
}
