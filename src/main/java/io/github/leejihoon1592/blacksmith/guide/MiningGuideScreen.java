package io.github.leejihoon1592.blacksmith.guide;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class MiningGuideScreen extends Screen {
    private MiningGuideScreen() {
        super(Component.translatable("item.blacksmith.guidebook"));
    }

    static void open() {
        Minecraft.getInstance().setScreen(new MiningGuideScreen());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        int x = (width - 236) / 2;
        int y = (height - 152) / 2;
        graphics.fill(x - 3, y - 3, x + 239, y + 155, 0xff594236);
        graphics.fill(x, y, x + 236, y + 152, 0xffefe3c6);
        graphics.drawString(font, title, x + 12, y + 10, 0xff71543c, false);
        graphics.drawString(font, Component.translatable("guide.blacksmith.mining"), x + 12, y + 28, 0xff30251d, false);
        graphics.fill(x + 12, y + 43, x + 224, y + 44, 0xffb99f79);
        graphics.renderItem(new ItemStack(Items.IRON_PICKAXE), x + 12, y + 49);
        graphics.drawString(font, Component.translatable("guide.blacksmith.iron"), x + 36, y + 54, 0xff30251d, false);
        graphics.renderItem(new ItemStack(Items.DIAMOND_PICKAXE), x + 12, y + 72);
        graphics.drawString(font, Component.translatable("guide.blacksmith.diamond"), x + 36, y + 77, 0xff30251d, false);
        graphics.drawWordWrap(font, Component.translatable("guide.blacksmith.enchantments"), x + 12, y + 103, 212, 0xff594236);
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
