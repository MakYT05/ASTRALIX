package org.zeith.modid.custom.entyties;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.modid.init.EntitiesMI;
import org.zeith.modid.init.ItemsMI;

public class RumarukaMob extends PathfinderMob implements net.minecraft.world.item.trading.Merchant {
    private final MerchantOffers offers = new MerchantOffers();

    private Player tradingPlayer;

    public RumarukaMob(EntityType<? extends PathfinderMob> type, Level world) {
        super(type, world);
        updateOffers();
    }

    private void updateOffers() {
        offers.add(new MerchantOffer(new ItemStack(ItemsMI.ASTRALCOIN, 3), new ItemStack(ItemsMI.ASTRAL_BEER, 1), 10, 2, 0.05F));
        offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.GOLD_INGOT, 2), 10, 2, 0.05F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FLYING_SPEED, 0.1F)
                .add(Attributes.MOVEMENT_SPEED, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    public void tick() {
        super.tick();
        this.setYRot(this.yHeadRot);
    }

    @Override
    public InteractionResult interactAt(Player player, net.minecraft.world.phys.Vec3 vec, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            this.tradingPlayer = player;

            updateOffers();

            MenuProvider container = new SimpleMenuProvider(
                    (id, inventory, p) -> new MerchantMenu(id, inventory, this),
                    Component.literal("Торговля с Румарукой")
            );
            player.openMenu(container);
            return InteractionResult.SUCCESS;
        }
        return super.interactAt(player, vec, hand);
    }

    @Override
    public MerchantOffers getOffers() {
        return offers;
    }

    @Override
    public void overrideOffers(MerchantOffers offers) {}

    @Override
    public void setTradingPlayer(Player player) { this.tradingPlayer = player; }

    @Override
    public Player getTradingPlayer() { return this.tradingPlayer; }

    @Override
    public int getVillagerXp() { return 10; }

    @Override
    public void overrideXp(int xp) {}

    @Override
    public boolean showProgressBar() { return false; }

    @Override
    public SoundEvent getNotifyTradeSound() { return null; }

    @Override
    public boolean isClientSide() { return false; }

    @Override
    public void notifyTrade(MerchantOffer offer) {}

    @Override
    public void notifyTradeUpdated(ItemStack stack) {}

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntitiesMI.RUMARUKA_MOB, RumarukaMob.createAttributes().build());
    }
}