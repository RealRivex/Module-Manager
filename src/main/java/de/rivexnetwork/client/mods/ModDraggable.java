package de.rivexnetwork.client.mods;

import de.rivexnetwork.client.hud.IRenderer;
import de.rivexnetwork.client.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {

    public final int getLineOffset(ScreenPosition pos, int lineNum) {
        return (int) pos.getAbsoluteY() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum) {
        return (minecraft.fontRenderer.FONT_HEIGHT + 3) * lineNum;
    }
}
