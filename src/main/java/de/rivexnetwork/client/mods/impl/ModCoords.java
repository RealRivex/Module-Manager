package de.rivexnetwork.client.mods.impl;

import de.rivexnetwork.client.hud.ScreenPosition;
import de.rivexnetwork.client.mods.ModDraggable;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class ModCoords extends ModDraggable {

    private ScreenPosition pos;

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
    }

    @Override
    public ScreenPosition load() {
        return pos;
    }

    @Override
    public int getWidth() {
        return fontRenderer.getStringWidth("X: XXX.XXX.XXX,XX");
    }

    @Override
    public int getHeight() {
        return (fontRenderer.FONT_HEIGHT * 3) + 2;
    }

    @Override
    public void render(ScreenPosition pos) {
        fontRenderer.drawString(matrixStack, "" + String.format("X: %.2f", Minecraft.getInstance().renderViewEntity.getPosX()),
                (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, new Color(255, 150, 0).getRGB());
        fontRenderer.drawString(matrixStack, "" + String.format("Y: %.2f", Minecraft.getInstance().renderViewEntity.getBoundingBox().minY),
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + fontRenderer.FONT_HEIGHT, new Color(255, 150, 0).getRGB());
        fontRenderer.drawString(matrixStack, "" + String.format("Z: %.2f", Minecraft.getInstance().renderViewEntity.getPosZ()),
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + (fontRenderer.FONT_HEIGHT * 2), new Color(255, 150, 0).getRGB());
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        fontRenderer.drawString(matrixStack, "666.66",
                (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, new Color(255, 150, 0).getRGB());
        fontRenderer.drawString(matrixStack, "66.66",
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + fontRenderer.FONT_HEIGHT, new Color(255, 150, 0).getRGB());
        fontRenderer.drawString(matrixStack, "666.66",
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + (fontRenderer.FONT_HEIGHT * 2), new Color(255, 150, 0).getRGB());
    }
}