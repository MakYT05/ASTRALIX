package org.zeith.modid.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.zeith.modid.custom.entyties.LightningBoltProjectile;

public class LightningBoltRenderer extends EntityRenderer<LightningBoltProjectile> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("modid", "textures/entity/lightning_ball.png");

    public LightningBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(LightningBoltProjectile entity) {
        return TEXTURE;
    }

    @Override
    public void render(LightningBoltProjectile entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();

        matrixStack.translate(0.0, 0.0, 0.0);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity)));
        PoseStack.Pose pose = matrixStack.last();
        Matrix4f matrix = pose.pose();
        Matrix3f normal = pose.normal();

        vertex(vertexConsumer, matrix, normal, -0.5F, -0.5F, 0, 1, 1, packedLight);
        vertex(vertexConsumer, matrix, normal, 0.5F, -0.5F, 0, 0, 1, packedLight);
        vertex(vertexConsumer, matrix, normal, 0.5F, 0.5F, 0, 0, 0, packedLight);
        vertex(vertexConsumer, matrix, normal, -0.5F, 0.5F, 0, 1, 0, packedLight);

        matrixStack.popPose();
    }

    private void vertex(VertexConsumer vertexConsumer, Matrix4f matrix, Matrix3f normal, float x, float y, float z, float u, float v, int packedLight) {
        vertexConsumer.vertex(matrix, x, y, z).color(255, 255, 255, 255).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
    }
}