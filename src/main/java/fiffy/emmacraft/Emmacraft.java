package fiffy.emmacraft;

import fiffy.emmacraft.block.Glape;
import fiffy.emmacraft.item.Scroll;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Emmacraft implements ModInitializer {
	public static final String MOD_ID = "emmacraft";
	public static final String MOD_NAME = "Emmacraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Emmacraft");

		LOGGER.info("Adding 'custom' to the item group registry.");
		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "custom"), CUSTOM_ITEM_GROUP);

		LOGGER.info("Adding 'scroll' to the item registry.");
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "scroll"), SCROLL_ITEM);

		LOGGER.info("Adding 'glape' to the item registry.");
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "glape"), new BlockItem(GLAPE_BLOCK, new Item.Settings()));

		LOGGER.info("Adding 'glape' to the block registry.");
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "glape"), GLAPE_BLOCK);

		LOGGER.info("Adding 'glape' to the fuel registry.");
		FuelRegistry.INSTANCE.add(GLAPE_BLOCK, 300);

		LOGGER.info("Adding 'glape' to the composting chance registry.");
		CompostingChanceRegistry.INSTANCE.add(GLAPE_BLOCK, 1.0F);
	}

	// Custom items
	public static final Item SCROLL_ITEM = new Scroll(new Item.Settings());

	// Custom blocks
	public static final Block GLAPE_BLOCK = new Glape(Block.Settings.create().strength(4.0f).requiresTool());

	// Custom item group
	private static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(SCROLL_ITEM))
			.displayName(Text.translatable("itemGroup.emmacraft.custom"))
			.entries((context, entries) -> {
				entries.add(SCROLL_ITEM);
				entries.add(GLAPE_BLOCK);
			})
			.build();
}