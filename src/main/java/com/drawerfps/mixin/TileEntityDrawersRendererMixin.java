package com.drawerfps.mixin;

import com.drawerfps.DrawerFPS;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.client.renderer.TileEntityDrawersRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityDrawersRenderer.class)
public class TileEntityDrawersRendererMixin {
    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void render(TileEntityDrawers tile, float partialTickTime, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay, CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;
        BlockPos blockPos = tile.getBlockPos();

        if (player != null
                && player.blockPosition().distSqr(blockPos) > DrawerFPS.config.getCommonConfig().renderRange.get() * DrawerFPS.config.getCommonConfig().renderRange.get()) {
            ci.cancel();
        }
    }
}
