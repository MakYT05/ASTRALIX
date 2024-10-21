package org.zeith.modid.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.custom.entyties.*;

@SimplyRegister
public interface EntitiesMI {
    @RegistryName("lightning_ball")
    EntityType<LightningBoltProjectile> LIGHTNING_BALL = EntityType.Builder.<LightningBoltProjectile>of(LightningBoltProjectile::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .setTrackingRange(80)
            .setUpdateInterval(1)
            .build("lightning_ball");

    @RegistryName("zeith_mob")
    EntityType<ZeithMob> ZEITH_MOB = EntityType.Builder.of(ZeithMob::new, MobCategory.MONSTER)
            .sized(1F, 1.5F)
            .build("zeith_mob");

    @RegistryName("moontlex_mob")
    EntityType<MoontlexMob> MOONTLEX_MOB = EntityType.Builder.of(MoontlexMob::new, MobCategory.MONSTER)
            .sized(1F, 1.5F)
            .build("moontlex_mob");

    @RegistryName("astral_zombie")
    EntityType<AstralZombieMod> ASTRAL_ZOMBIE = EntityType.Builder.of(AstralZombieMod::new, MobCategory.MONSTER)
            .sized(1F, 1.5F)
            .build("astral_zombie");

    @RegistryName("rumaruka_mob")
    EntityType<RumarukaMob> RUMARUKA_MOB = EntityType.Builder.of(RumarukaMob::new, MobCategory.MONSTER)
            .sized(1F, 1.5F)
            .build("rumaruka_mob");
}