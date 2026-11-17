package io.github.leejihoon1592.blacksmith.guide;

import io.github.leejihoon1592.blacksmith.BlacksmithMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public final class GuideNetwork {
    private static final String READ_KEY = "blacksmith.read.first_mining";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(BlacksmithMod.MODID, "guide"), () -> "1", "1"::equals, "1"::equals);

    private record Open(GuidebookItem.Book book, boolean read) {}
    private record ReadMining() {}

    public static void register() {
        CHANNEL.messageBuilder(Open.class, 0, NetworkDirection.PLAY_TO_CLIENT)
                .encoder((msg, buf) -> { buf.writeEnum(msg.book()); buf.writeBoolean(msg.read()); })
                .decoder(buf -> new Open(buf.readEnum(GuidebookItem.Book.class), buf.readBoolean()))
                .consumerMainThread((msg, ctx) -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                        () -> () -> MiningGuideScreen.open(msg.book(), msg.read())))
                .add();
        CHANNEL.messageBuilder(ReadMining.class, 1, NetworkDirection.PLAY_TO_SERVER)
                .encoder((msg, buf) -> {})
                .decoder(buf -> new ReadMining())
                .consumerMainThread((msg, ctx) -> {
                    ServerPlayer player = ctx.get().getSender();
                    if (player != null) {
                        CompoundTag root = player.getPersistentData();
                        CompoundTag data = root.getCompound(Player.PERSISTED_NBT_TAG);
                        data.putBoolean(READ_KEY, true);
                        root.put(Player.PERSISTED_NBT_TAG, data);
                    }
                }).add();
    }

    static void open(ServerPlayer player, GuidebookItem.Book book) {
        boolean read = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG).getBoolean(READ_KEY);
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new Open(book, read));
    }

    static void markMiningRead() {
        CHANNEL.sendToServer(new ReadMining());
    }

    private GuideNetwork() {}
}
