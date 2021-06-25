package de.rivexnetwork.client.mods;

import de.rivexnetwork.client.hud.HUDManager;
import de.rivexnetwork.client.hud.IRenderer;
import de.rivexnetwork.client.mods.impl.ModCoords;
import de.rivexnetwork.client.mods.impl.ModFps;

public class Mods {

    private static IRenderer[] mods = new IRenderer[]{new ModFps(), new ModCoords()};

    public static void register(HUDManager hm) {
        hm.register(mods);
    }
}
