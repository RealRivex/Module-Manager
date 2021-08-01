package de.rivexnetwork.rivexclient.mods;

import de.rivexnetwork.rivexclient.hud.IRenderer;
import de.rivexnetwork.rivexclient.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {

    public final int getLineOffset(ScreenPosition pos, int lineNum) {
        return (int) pos.getAbsoluteY() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum) {
        return (minecraft.fontRenderer.lineHeight + 3) * lineNum;
    }
}
