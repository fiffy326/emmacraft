package fiffy.emmacraft.mixin.client;

import static fiffy.emmacraft.Emmacraft.LOGGER;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class EmmacraftClientMixin {
	@Inject(method = "run", at = @At("HEAD"))
	private void init(CallbackInfo info) {
		LOGGER.info("EmmacraftClientMixin injected into the start of MinecraftClient.run()");
	}
}