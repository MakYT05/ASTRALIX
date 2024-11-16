//package org.zeith.modid.Enchant;
//
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.event.entity.living.LivingDeathEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import org.zeith.modid.init.ItemsMI;
//
//import java.util.Random;
//
//import static org.zeith.modid.Astralix.ASTRALSCRIPT_ENCHANTMENT;
//
//public class EnchantListener {
//    @SubscribeEvent
//    public static void onLivingEntityDeath(LivingDeathEvent event) {
//        Entity entity = event.getEntity();
//
//        if (entity instanceof Monster) {
//            Player player = entity.level().getNearestPlayer(entity, 10);
//
//            if (player != null && player.getMainHandItem().isEnchanted()) {
//                int level = player.getMainHandItem().getEnchantmentLevel(ASTRALSCRIPT_ENCHANTMENT.get());
//
//                if (level > 0) {
//                    Random rand = new Random();
//                    int chance = level * 10;
//
//                    if (rand.nextInt(100) < chance) {
//                        ItemStack item = new ItemStack(ItemsMI.ASTRALSCRIPT);
//                        entity.spawnAtLocation(item);
//                    }
//                }
//            }
//        }
//    }
//}