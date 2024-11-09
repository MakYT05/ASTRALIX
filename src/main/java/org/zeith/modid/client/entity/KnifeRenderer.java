package org.zeith.modid.client.entity;

import com.mojang.blaze3d.MethodsReturnNonnullByDefault;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.zeith.hammeranims.api.HammerAnimationsApi;
import org.zeith.hammeranims.api.geometry.event.RefreshStaleModelsEvent;
import org.zeith.hammeranims.api.geometry.model.IGeometricModel;
import org.zeith.hammeranims.api.geometry.model.RenderData;
import org.zeith.modid.custom.entyties.Knife;
import org.zeith.modid.init.ModelsMI;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class KnifeRenderer extends EntityRenderer<Knife> {
    private static final ResourceLocation texture = new ResourceLocation("modid", "textures/entity/knife.png");

    IGeometricModel KnifeModel;
    final RenderData data;

    public KnifeRenderer(EntityRendererProvider.Context context) {
        super(context);
        data = new RenderData();
        HammerAnimationsApi.EVENT_BUS.addListener(this::refreshModel);
    }

    @Override
    public ResourceLocation getTextureLocation(Knife p_114482_) {
        return texture;
    }

    @Override
    public void render(Knife pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        KnifeModel.resetPose();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.01f, 0.5F);
        pPoseStack.scale(2f, 2f, 2f);
        KnifeModel.renderModel(data.apply(pPoseStack,pBuffer.getBuffer(RenderType.entitySolid(texture)),pPackedLight, OverlayTexture.NO_OVERLAY));

        pPoseStack.popPose();
    }

    public void refreshModel(RefreshStaleModelsEvent e) { KnifeModel = ModelsMI.KNIFE_MODEL.createModel(); }
}