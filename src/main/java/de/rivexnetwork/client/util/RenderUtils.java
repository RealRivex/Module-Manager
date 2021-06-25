package de.rivexnetwork.client.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;

public class RenderUtils extends AbstractGui {

    public static MatrixStack matrixStack;

    public RenderUtils() {
        matrixStack = new MatrixStack();
        matrixStack.push();
    }

    public static void drawHorizontalLine(int minX, int maxX, int offsetY, int color) {
        fill_double(matrixStack, minX, offsetY, maxX, offsetY + 1, color);
    }

    public static void drawVerticalLine(int offsetX, int minY, int maxY, int color) {
        fill_double(matrixStack, offsetX, minY, offsetX - 1, maxY, color);
    }

    public static void drawHorizontalLine(double minX, double maxX, double offsetY, int color) {
        fill_double(matrixStack, (int) minX, (int) offsetY, (int) maxX, (int) offsetY + 1, color);
    }

    public static void drawVerticalLine(double offsetX, double minY, double maxY, int color) {
        fill_double(matrixStack, (int) offsetX, (int) minY, (int) offsetX - 1, (int) maxY, color);
    }

    public static void drawRect(int x1, int x2, int y1, int y2, int color) {
        drawHorizontalLine(x1 + 1, x2, y1, color);
        drawHorizontalLine(x1, x2, y2 - 1, color);
        drawVerticalLine(x1 + 1, y1, y2, color);
        drawVerticalLine(x2, y1, y2, color);
    }

    public static void drawRect_double(double x1, double x2, double y1, double y2, int color) {
        drawHorizontalLine(x1 + 1, x2, y1, color);
        drawHorizontalLine(x1, x2, y2 - 1, color);
        drawVerticalLine(x1 + 1, y1, y2, color);
        drawVerticalLine(x2, y1, y2, color);
    }
}
