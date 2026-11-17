package io.github.leejihoon1592.blacksmith.guide;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public final class GuidebookItem extends Item {
    public enum Book {
        PRIMER("guidebook"), MATERIALS("material_journal"), WEAPONS("weapon_ledger");

        final String id;

        Book(String id) {
            this.id = id;
        }
    }

    private final Book book;

    public GuidebookItem(Book book) {
        super(new Item.Properties().stacksTo(1));
        this.book = book;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> MiningGuideScreen.open(book));
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }
}
