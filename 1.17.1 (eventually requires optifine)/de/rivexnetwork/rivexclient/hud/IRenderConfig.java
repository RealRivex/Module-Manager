package de.rivexnetwork.rivexclient.hud;

public interface IRenderConfig {

    void save(ScreenPosition pos);

    public ScreenPosition load();
}
