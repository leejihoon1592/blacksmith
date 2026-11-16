package com.example.examplemod;

import java.util.List;
import java.util.ArrayList;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

final class MetalIngots {
    static final List<RegistryObject<? extends Item>> OTHER_MATERIALS = new ArrayList<>();
    static final List<RegistryObject<MetalIngotItem>> ALL = List.of(
            ingot("duracilium", 8, 7, 5, 3.5, 1.5),
            ingot("enduri", 9, 1, 7, 4.5, 3.5),
            ingot("tenaloct", 8, 3.5, 1.5, 7, 5),
            ingot("stevalite", 9, 4.5, 3, 1.5, 7),
            ingot("accutyrone", 6.5, 9, 4.5, 3.5, 1.5),
            ingot("charvin", 1.5, 9, 6.5, 4.5, 3.5),
            ingot("clinsor", 3, 9, 1.5, 7, 4.5),
            ingot("edgastium", 5, 8, 3.5, 1.5, 7),
            ingot("criminaltalloi", 7, 4.5, 8.5, 3.5, 1.5),
            ingot("brackstal", 1, 6.5, 9, 5, 3.5),
            ingot("durathorn", 3.5, 1.5, 8.5, 6.5, 5),
            ingot("granteron", 4, 3.5, 9, 1.5, 7),
            ingot("flexarite", 6, 5, 3.5, 9, 1.5),
            ingot("sprinal", 1, 7, 5, 8.5, 3.5),
            ingot("liselborn", 3.5, 1, 7, 8.5, 5),
            ingot("biklis", 4.5, 3.5, 1.5, 8.5, 7),
            ingot("conductite", 7, 5, 2.5, 1.5, 9),
            ingot("voltarite", 1.5, 7, 4.5, 3, 9),
            ingot("lumesor", 3.5, 1.5, 6.5, 4.5, 9),
            ingot("ginspa", 5, 3.5, 1, 7, 8.5)
    );

    private static RegistryObject<MetalIngotItem> ingot(String id, double durability, double polish,
            double hardness, double elasticity, double conductivity) {
        OTHER_MATERIALS.addAll(MetalForms.register(id));
        var stats = new MetalIngotItem.Stats(durability, polish, hardness, elasticity, conductivity);
        return ExampleMod.ITEMS.register(id + "_ingot", () -> new MetalIngotItem(stats));
    }

    private MetalIngots() {}
}
