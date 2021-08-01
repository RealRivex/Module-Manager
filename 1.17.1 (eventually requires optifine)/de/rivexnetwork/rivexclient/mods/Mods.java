package de.rivexnetwork.rivexclient.mods;

import de.rivexnetwork.rivexclient.hud.HUDManager;
import de.rivexnetwork.rivexclient.hud.IRenderer;
import de.rivexnetwork.rivexclient.mods.impl.ModCoords;
import de.rivexnetwork.rivexclient.mods.impl.ModFps;

public class Mods {

    private static IRenderer[] mods = new IRenderer[]{new ModFps(), new ModCoords()};

    public static void register(HUDManager hm) {
        hm.register(mods);
    }
}
