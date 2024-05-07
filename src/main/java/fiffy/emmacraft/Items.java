package fiffy.emmacraft;

import fiffy.emmacraft.item.Scroll;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static fiffy.emmacraft.Blocks.GLAPE_BLOCK;
import static fiffy.emmacraft.Emmacraft.LOGGER;
import static fiffy.emmacraft.Emmacraft.MOD_ID;

public abstract class Items {
    // Custom items
    public static final Item GLAPE_ITEM = new BlockItem(GLAPE_BLOCK, new Item.Settings());
    public static final Item SCROLL_ITEM = new Scroll(new Item.Settings());

    /**
     * Registers custom items.
     */
    public static void register() {
        registerItem("glape", GLAPE_ITEM);
        registerItem("scroll", SCROLL_ITEM);
    }

    /**
     * Registers a new item.
     *
     * @param name The item identifier.
     * @param item The item object.
     */
    private static void registerItem(String name, Item item) {
        LOGGER.info("Adding '{}' to the item registry", name);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }
}
