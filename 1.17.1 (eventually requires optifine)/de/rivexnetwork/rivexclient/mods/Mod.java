package de.rivexnetwork.rivexclient.mods;

import com.mojang.blaze3d.vertex.PoseStack;
import de.rivexnetwork.rivexclient.Client;
import de.rivexnetwork.rivexclient.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public class Mod {

    private boolean isEnabled = true;

    protected final Minecraft minecraft;

    protected final Font fontRenderer;

    protected final Client client;

    public PoseStack matrixStack = new PoseStack();

    public Mod() {
        this.minecraft = Minecraft.getInstance();
        this.fontRenderer = minecraft.fontRenderer;
        this.client = Client.getInstance();
        setEnabled(isEnabled);

        matrixStack.pushPose();
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;

        if (isEnabled) {
            EventManager.register(this);
        } else {
            EventManager.unregister(this);
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
