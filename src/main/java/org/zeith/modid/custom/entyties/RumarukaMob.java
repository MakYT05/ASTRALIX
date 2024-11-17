package org.zeith.modid.custom.entyties;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.hammeranims.api.animsys.AnimationSystem;
import org.zeith.hammeranims.api.animsys.CommonLayerNames;
import org.zeith.hammeranims.api.animsys.layer.AnimationLayer;
import org.zeith.hammeranims.api.tile.IAnimatedEntity;
import org.zeith.modid.init.AnimationsMI;
import org.zeith.modid.init.EntitiesMI;
import org.zeith.modid.init.ItemsMI;

import javax.annotation.Nullable;

public class RumarukaMob extends PathfinderMob implements Merchant, IAnimatedEntity {
    protected final AnimationSystem animations = AnimationSystem.create(this);
    private final MerchantOffers offers = new MerchantOffers();
    private Player tradingPlayer;

    public RumarukaMob(EntityType<? extends PathfinderMob> type, Level world) {
        super(type, world);
        updateOffers();
    }

    private void updateOffers() {
        offers.clear();
        offers.add(new MerchantOffer(new ItemStack(ItemsMI.ASTRALCOIN, 3), new ItemStack(ItemsMI.ASTRAL_BEER, 1), 10, 2, 0.05F));
        offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.GOLD_INGOT, 2), 10, 2, 0.05F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    public InteractionResult interactAt(Player player, net.minecraft.world.phys.Vec3 vec, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            this.setTradingPlayer(player);

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
    public MerchantOffers getOffers() { return offers; }

    @Override
    public void overrideOffers(MerchantOffers offers) {
        offers.clear();
        offers.addAll(this.offers);
    }

    @Override
    public void setTradingPlayer(Player player) { this.tradingPlayer = player; }

    @Nullable
    @Override
    public Player getTradingPlayer() { return this.tradingPlayer; }

    @Override
    public int getVillagerXp() { return 10; }

    @Override
    public void overrideXp(int xp) {}

    @Override
    public boolean showProgressBar() { return true; }

    @Override
    public SoundEvent getNotifyTradeSound() { return SoundEvents.VILLAGER_YES; }

    @Override
    public boolean isClientSide() { return this.level().isClientSide; }

    @Override
    public void notifyTrade(MerchantOffer offer) {}

    @Override
    public void notifyTradeUpdated(ItemStack stack) {}

    @Override
    public void tick() {
        super.tick();

        if (this.getDeltaMovement().lengthSqr() > 0.001) {
            if (AnimationsMI.RUMARUKA_ANIMATION_GO != null) {
                animations.startAnimationAt(CommonLayerNames.AMBIENT, AnimationsMI.RUMARUKA_ANIMATION_GO);
            }
        } else {
            animations.startAnimationAt(CommonLayerNames.ACTION, AnimationsMI.RUMARUKA_ANIMATION_GO);
        }

        float deltaYaw = Math.abs(this.yBodyRot - this.yBodyRotO);
        if (deltaYaw > 10.0F && AnimationsMI.RUMARUKA_ANIMATION_HEAD != null) {
            animations.startAnimationAt(CommonLayerNames.ACTION, AnimationsMI.RUMARUKA_ANIMATION_HEAD);
        } else {
            animations.startAnimationAt(CommonLayerNames.ACTION, AnimationsMI.RUMARUKA_ANIMATION_HEAD);
        }

        animations.tick();
    }

    @Override
    public void setupSystem(AnimationSystem.Builder builder) {
        builder.addLayers(
                AnimationLayer.builder(CommonLayerNames.AMBIENT).preventAutoSync(),
                AnimationLayer.builder(CommonLayerNames.ACTION)
        ).autoSync();
    }

    @Override
    public AnimationSystem getAnimationSystem() { return animations; }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("Animations", animations.serializeNBT());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        animations.deserializeNBT(nbt.getCompound("Animations"));
    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        animations.deserializeNBT(nbt.getCompound("Animations"));
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntitiesMI.RUMARUKA_MOB, RumarukaMob.createAttributes().build());
    }
}