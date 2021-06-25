package de.rivexnetwork.client.hud;

public interface IRenderConfig {

    void save(ScreenPosition pos);

    public ScreenPosition load();
}
