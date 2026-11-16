package com.example.examplemod;

import java.util.List;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

final class MetalForms {
    static List<RegistryObject<? extends Item>> register(String metal) {
        return List.of(
                item("raw_" + metal),
                item(metal + "_nugget"),
                block("raw_" + metal + "_block", Blocks.RAW_IRON_BLOCK),
                block(metal + "_ore", Blocks.IRON_ORE),
                block("deepslate_" + metal + "_ore", Blocks.DEEPSLATE_IRON_ORE),
                block("nether_" + metal + "_ore", Blocks.NETHER_GOLD_ORE),
                block("end_" + metal + "_ore", Blocks.END_STONE)
        );
    }

    private static RegistryObject<Item> item(String id) {
        return ExampleMod.ITEMS.register(id, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<BlockItem> block(String id, Block template) {
        var block = ExampleMod.BLOCKS.register(id, () -> new Block(BlockBehaviour.Properties.copy(template)));
        return ExampleMod.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private MetalForms() {}
}
