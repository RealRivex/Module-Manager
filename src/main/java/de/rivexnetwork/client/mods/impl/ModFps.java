package de.rivexnetwork.client.mods.impl;

import de.rivexnetwork.client.hud.ScreenPosition;
import de.rivexnetwork.client.mods.ModDraggable;
import net.minecraft.client.Minecraft;

public class ModFps extends ModDraggable {

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
        return fontRenderer.getStringWidth("FPS: 999");
    }

    @Override
    public int getHeight() {
        return fontRenderer.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        fontRenderer.drawString(matrixStack, "FPS: " + Minecraft.debugFPS, (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, 1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        fontRenderer.drawString(matrixStack, "FPS: 999", (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, 1);
    }
}
