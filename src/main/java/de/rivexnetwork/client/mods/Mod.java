package de.rivexnetwork.client.mods;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.rivexnetwork.client.event.EventManager;
import de.rivexnetwork.client.main.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {

    private boolean isEnabled = true;

    protected final Minecraft minecraft;

    protected final FontRenderer fontRenderer;

    protected final Client client;

    public MatrixStack matrixStack = new MatrixStack();

    public Mod() {
        this.minecraft = Minecraft.getInstance();
        this.fontRenderer = minecraft.fontRenderer;
        this.client = Client.getInstance();
        setEnabled(isEnabled);

        matrixStack.push();
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
