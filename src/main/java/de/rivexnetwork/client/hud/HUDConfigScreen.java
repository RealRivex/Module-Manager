package de.rivexnetwork.client.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.rivexnetwork.client.util.RenderUtils;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class HUDConfigScreen extends Screen {

    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<>();

    private Optional<IRenderer> selectedRenderer = Optional.empty();

    private double prevX, prevY;


    public HUDConfigScreen(HUDManager hm) {
        super(new StringTextComponent(""));
        Collection<IRenderer> registeredRenderers = hm.getRegisteredRenderers();

        for (IRenderer renderer : registeredRenderers) {
            if (!renderer.isEnabled()) {
                continue;
            }
            ScreenPosition pos = renderer.load();
            if (pos == null) {
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }
            adjustBounds(renderer, pos);
            this.renderers.put(renderer, pos);
        }
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);

        final float zBackup = itemRenderer.zLevel;
        itemRenderer.zLevel = 200;

        RenderUtils.drawRect(0, width, 0, height, new Color(255, 0, 0).getRGB());

        for (IRenderer renderer : renderers.keySet()) {
            ScreenPosition pos = renderers.get(renderer);
            renderer.renderDummy(pos);

            //this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), new Color(0, 255, 255).getRGB());

        }

        this.itemRenderer.zLevel = zBackup;
    }

    private void drawHollowRect(double x, double y, double w, double h, int c) {
        RenderUtils.drawHorizontalLine(x, x + w, y, c);
        RenderUtils.drawHorizontalLine(x, x + w, y + h, c);
        RenderUtils.drawVerticalLine(x, y + h, y, c);
        RenderUtils.drawVerticalLine(x + w, y + h, y, c);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (selectedRenderer.isPresent()) {
            moveSelectedRenderBy(mouseX - prevX, mouseY - prevY);
        }
        this.prevX = mouseX;
        this.prevY = mouseY;
        return true;
    }

    private void moveSelectedRenderBy(double offsetX, double offsetY) {
        IRenderer renderer = selectedRenderer.get();
        ScreenPosition pos = renderers.get(renderer);
        pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);
        adjustBounds(renderer, pos);
    }

    @Override
    public void onClose() {
        for (IRenderer renderer : renderers.keySet()) {
            renderer.save(renderers.get(renderer));
        }
    }

    private void adjustBounds(IRenderer renderer, ScreenPosition pos) {
        MainWindow window = Minecraft.getInstance().getMainWindow();
        int width = window.getScaledWidth();
        int height = window.getScaledHeight();

        double absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(width - renderer.getWidth(), 0)));
        double absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(height - renderer.getHeight(), 0)));

        pos.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    public boolean mouseClicked(double x, double y, int button) {
        this.prevX = (int) x;
        this.prevY = (int) y;
        loadMouseOver(x, y);
        return super.mouseClicked(x, y, button);
    }

    private void loadMouseOver(double x, double y) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRenderer> {

        private final double mouseX;
        private final double mouseY;

        public MouseOverFinder(double x, double y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public boolean test(IRenderer renderer) {
            ScreenPosition pos = renderers.get(renderer);
            double absoluteX = pos.getAbsoluteX();
            double absoluteY = pos.getAbsoluteY();
            if (mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {
                if (mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
                    return true;
                }
            }
            return false;
        }
    }
}
