package de.rivexnetwork.rivexclient.mods.impl;

import de.rivexnetwork.rivexclient.hud.ScreenPosition;
import de.rivexnetwork.rivexclient.mods.ModDraggable;
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
        return fontRenderer.width("X: XXX");
    }

    @Override
    public int getHeight() {
        return (fontRenderer.lineHeight * 3) + 2;
    }

    @Override
    public void render(ScreenPosition pos) {
        fontRenderer.draw(matrixStack, "" + String.format("X: %.2f", Minecraft.getInstance().player.getX()),
                (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, new Color(255, 150, 0).getRGB());
        fontRenderer.draw(matrixStack, "" + String.format("Y: %.2f", Minecraft.getInstance().player.getY()),
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + fontRenderer.lineHeight, new Color(255, 150, 0).getRGB());
        fontRenderer.draw(matrixStack, "" + String.format("Z: %.2f", Minecraft.getInstance().player.getZ()),
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + (fontRenderer.lineHeight * 2), new Color(255, 150, 0).getRGB());
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        fontRenderer.draw(matrixStack, "" + String.format("X: %.2f", Minecraft.getInstance().player.getX()),
                (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, new Color(255, 150, 0).getRGB());
        fontRenderer.draw(matrixStack, "" + String.format("Y: %.2f", Minecraft.getInstance().player.getY()),
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + fontRenderer.lineHeight, new Color(255, 150, 0).getRGB());
        fontRenderer.draw(matrixStack, "" + String.format("Z: %.2f", Minecraft.getInstance().player.getZ()),
                (float) pos.getAbsoluteX() + 1, (float) (pos.getAbsoluteY() + 1) + (fontRenderer.lineHeight * 2), new Color(255, 150, 0).getRGB());
    }
}