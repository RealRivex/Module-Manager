package de.rivexnetwork.client.hud;

import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;

public class ScreenPosition {

    MainWindow window = Minecraft.getInstance().getMainWindow();
    private static final Minecraft mc = Minecraft.getInstance();

    private double x, y;

    public ScreenPosition(double x, double y) {
        setRelative(x, y);
    }

    public ScreenPosition(int x, int y) {
        setAbsolute(x, y);
    }

    public static ScreenPosition fromRelativePosition(double x, double y) {
        return new ScreenPosition(x, y);
    }

    public static ScreenPosition fromAbsolute(int x, int y) {
        return new ScreenPosition(x, y);
    }

    public double getAbsoluteX() {
        return x;
    }

    public double getAbsoluteY() {
        return y;
    }

    public double getRelativeX() {
        return x / (double) window.getScaledWidth();
    }

    public double getRelativeY() {
        return y / (double) window.getScaledHeight();
    }

    public void setAbsolute(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setRelative(double x, double y) {
        this.x = window.getScaledWidth() / x;
        this.y = window.getScaledHeight() / y;
    }

}