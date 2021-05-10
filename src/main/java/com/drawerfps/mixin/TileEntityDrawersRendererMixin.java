package com.drawerfps.mixin;

import com.drawerfps.DrawerFPS;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.client.renderer.TileEntityDrawersRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityDrawersRenderer.class)
public class TileEntityDrawersRendererMixin
{
    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void test(
      final TileEntityDrawers tile,
      final float partialTickTime,
      final MatrixStack matrix,
      final IRenderTypeBuffer buffer,
      final int combinedLight,
      final int combinedOverlay,
      final CallbackInfo ci)
    {
        PlayerEntity player = Minecraft.getInstance().player;
        BlockPos blockPos = tile.getBlockPos();

        if (player != null
              && player.blockPosition().distSqr(blockPos) > DrawerFPS.config.getCommonConfig().renderRange.get() * DrawerFPS.config.getCommonConfig().renderRange.get())
        {
            ci.cancel();
        }
    }
}
