package fiffy.emmacraft.mixin;

import static fiffy.emmacraft.Emmacraft.LOGGER;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class EmmacraftMixin {
	@Inject(method = "loadWorld", at = @At("HEAD"))
	private void init(CallbackInfo info) {
		LOGGER.info("EmmacraftMixin injected into the start of MinecraftServer.loadWorld()");
	}
}