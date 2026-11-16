package io.github.leejihoon1592.blacksmith.material;

import io.github.leejihoon1592.blacksmith.BlacksmithMod;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ToolActions;

public final class MiningToolPredicate extends ItemPredicate {
    private final ResourceLocation id;
    private final Block referenceOre;

    private MiningToolPredicate(String name, Block referenceOre) {
        this.id = ResourceLocation.fromNamespaceAndPath(BlacksmithMod.MODID, name);
        this.referenceOre = referenceOre;
    }

    public static void registerTiers() {
        registerTier("iron_mining_tool", Blocks.DIAMOND_ORE);
        registerTier("diamond_mining_tool", Blocks.OBSIDIAN);
    }

    private static void registerTier(String name, Block referenceOre) {
        var predicate = new MiningToolPredicate(name, referenceOre);
        ItemPredicate.register(predicate.id, json -> predicate);
    }

    @Override
    public boolean matches(ItemStack stack) {
        return stack.canPerformAction(ToolActions.PICKAXE_DIG)
                && stack.isCorrectToolForDrops(referenceOre.defaultBlockState());
    }

    @Override
    public JsonObject serializeToJson() {
        var json = new JsonObject();
        json.addProperty("type", id.toString());
        return json;
    }
}
