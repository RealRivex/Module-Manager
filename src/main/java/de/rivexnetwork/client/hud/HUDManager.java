package de.rivexnetwork.client.hud;

import com.google.common.collect.Sets;
import de.rivexnetwork.client.event.EventHandler;
import de.rivexnetwork.client.event.EventManager;
import de.rivexnetwork.client.event.events.RenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class HUDManager {

    private HUDManager() {
    }

    public static HUDManager instance = null;

    public static HUDManager getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new HUDManager();
        EventManager.register(instance);
        return instance;
    }

    private Set<IRenderer> registeredRenderers = Sets.newHashSet();
    private Minecraft mc = Minecraft.getInstance();

    public void register(IRenderer... renderers) {
        this.registeredRenderers.addAll(Arrays.asList(renderers));
    }

    public void unregister(IRenderer... renderers) {
        for (IRenderer renderer : renderers) {
            this.registeredRenderers.remove(renderer);
        }
    }

    public Collection<IRenderer> getRegisteredRenderers() {
        return Sets.newHashSet(registeredRenderers);
    }

    public void openConfigScreen() {
        mc.displayGuiScreen(new HUDConfigScreen(this));
    }

    @EventHandler
    public void onRender(RenderEvent e) {
        if (mc.currentScreen == null || mc.currentScreen instanceof ContainerScreen || mc.currentScreen instanceof ChatScreen) {
            for (IRenderer renderer : registeredRenderers) {
                callRenderer(renderer);
            }
        }
    }

    private void callRenderer(IRenderer renderer) {
        if (!renderer.isEnabled()) {
            return;
        }
        ScreenPosition pos = renderer.load();
        if (pos == null) {
            pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
        }
        renderer.render(pos);
    }
}
