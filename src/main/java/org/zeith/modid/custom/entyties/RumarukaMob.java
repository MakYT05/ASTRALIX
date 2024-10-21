package org.zeith.modid.custom.entyties;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.zeith.modid.init.EntitiesMI;

public class RumarukaMob extends PathfinderMob{
    public RumarukaMob(EntityType<? extends PathfinderMob> type, Level world) { super(type, world); }

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

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) { event.put(EntitiesMI.RUMARUKA_MOB, RumarukaMob.createAttributes().build()); }
}