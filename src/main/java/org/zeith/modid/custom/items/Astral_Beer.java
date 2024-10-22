package org.zeith.modid.custom.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Astral_Beer extends Item
{
    public Astral_Beer(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        if (!world.isClientSide && entityLiving instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 1));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 1));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));

            player.playSound(SoundEvents.GENERIC_DRINK, 1.0F, 1.0F);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) { return UseAnim.DRINK; }

    @Override
    public int getUseDuration(ItemStack stack) { return 32; }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }
}