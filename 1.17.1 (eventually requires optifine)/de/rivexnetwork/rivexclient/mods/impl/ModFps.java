package de.rivexnetwork.rivexclient.mods.impl;

import de.rivexnetwork.rivexclient.hud.ScreenPosition;
import de.rivexnetwork.rivexclient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.optifine.reflect.Reflector;

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
        return fontRenderer.width("FPS: " + Reflector.Minecraft_debugFPS.getValue());
    }

    @Override
    public int getHeight() {
        return fontRenderer.lineHeight;
    }

    @Override
    public void render(ScreenPosition pos) {
        fontRenderer.draw(matrixStack, "FPS: " + Reflector.Minecraft_debugFPS.getValue(), (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, 1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        fontRenderer.draw(matrixStack, "FPS: " + Reflector.Minecraft_debugFPS.getValue(), (float) pos.getAbsoluteX() + 1, (float) pos.getAbsoluteY() + 1, 1);
    }
}
