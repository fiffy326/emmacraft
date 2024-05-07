package fiffy.emmacraft;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static fiffy.emmacraft.Blocks.GLAPE_BLOCK;
import static fiffy.emmacraft.Emmacraft.LOGGER;
import static fiffy.emmacraft.Emmacraft.MOD_ID;
import static fiffy.emmacraft.Items.SCROLL_ITEM;

public abstract class ItemGroups {
    // Custom item groups
    private static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(SCROLL_ITEM))
            .displayName(Text.translatable("itemGroup.emmacraft.custom"))
            .entries((context, entries) -> {
                entries.add(SCROLL_ITEM);
                entries.add(GLAPE_BLOCK);
            }).build();

    /**
     * Registers custom item groups.
     */
    public static void register() {
        registerItemGroup("custom", CUSTOM_ITEM_GROUP);
    }

    /**
     * Registers a new item group.
     *
     * @param group The item group object.
     */
    private static void registerItemGroup(String name, ItemGroup group) {
        LOGGER.info("Adding '{}' to the item group registry", name);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, name), group);
    }
}