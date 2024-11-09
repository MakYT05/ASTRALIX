package org.zeith.modid.client.render;

import net.minecraft.client.renderer.entity.EntityRenderers;
import org.zeith.modid.client.entity.*;
import org.zeith.modid.init.EntitiesMI;

public class ModEntityRenderers {

    public static void registerRenderers() {
        EntityRenderers.register(EntitiesMI.LIGHTNING_BALL, LightningBoltRenderer::new);
        EntityRenderers.register(EntitiesMI.METEORITE, MeteoriteRenderer::new);
        EntityRenderers.register(EntitiesMI.ZEITH_MOB, ZeithMobRenderer::new);
        EntityRenderers.register(EntitiesMI.MOONTLEX_MOB, MoontlexMobRenderer::new);
        EntityRenderers.register(EntitiesMI.ASTRAL_ZOMBIE, AstralZombieRenderer::new);
        EntityRenderers.register(EntitiesMI.RUMARUKA_MOB, RumarukaMobRenderer::new);
        EntityRenderers.register(EntitiesMI.KNIFE, KnifeRenderer::new);
    }
}