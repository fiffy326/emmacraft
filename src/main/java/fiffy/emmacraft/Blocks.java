package fiffy.emmacraft;

import fiffy.emmacraft.block.Glape;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;

import static fiffy.emmacraft.Emmacraft.LOGGER;
import static fiffy.emmacraft.Emmacraft.MOD_ID;

public abstract class Blocks {
    // Custom blocks
    public static final Block GLAPE_BLOCK = new Glape(Block.Settings.create().strength(4.0f).requiresTool());

    // Record block names for more readable startup logs
    private static final HashMap<Block, String> blockNames = new HashMap<>();

    /**
     * Registers custom blocks.
     */
    public static void register() {
        registerBlock("glape", GLAPE_BLOCK, 300, 1.0f);
    }

    /**
     * Registers a new block.
     *
     * @param name  The block identifier.
     * @param block The block object.
     */
    private static void registerBlock(String name, Block block) {
        LOGGER.info("Adding '{}' to the block registry", name);
        blockNames.put(block, name);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
    }

    /**
     * Registers a new block and adds it to the fuel registry.
     *
     * @param name      The block identifier.
     * @param block     The block object.
     * @param fuelValue The fuel value.
     */
    private static void registerBlock(String name, Block block, int fuelValue) {
        registerBlock(name, block);
        registerFuel(block, fuelValue);
    }

    /**
     * Registers a new block and adds it to the composting chance registry.
     *
     * @param name             The block identifier.
     * @param block            The block object.
     * @param compostingChance The composting chance.
     */
    private static void registerBlock(String name, Block block, float compostingChance) {
        registerBlock(name, block);
        registerCompostable(block, compostingChance);
    }

    /**
     * Registers a new block and adds it to the fuel and composting chance registries.
     *
     * @param name             The block identifier.
     * @param block            The block object.
     * @param fuelValue        The fuel value.
     * @param compostingChance The composting chance.
     */
    private static void registerBlock(String name, Block block, int fuelValue, float compostingChance) {
        registerBlock(name, block);
        registerFuel(block, fuelValue);
        registerCompostable(block, compostingChance);
    }

    /**
     * Register a block as fuel.
     *
     * @param block     The block object.
     * @param fuelValue The fuel value.
     */
    private static void registerFuel(Block block, int fuelValue) {
        LOGGER.info("Adding '{}' to the fuel registry", blockNames.get(block));
        FuelRegistry.INSTANCE.add(block, fuelValue);
    }

    /**
     * Register a block as compostable.
     *
     * @param block            The block object.
     * @param compostingChance The composting chance.
     */
    private static void registerCompostable(Block block, float compostingChance) {
        LOGGER.info("Adding '{}' to the composting chance registry", blockNames.get(block));
        CompostingChanceRegistry.INSTANCE.add(block, compostingChance);
    }
}
