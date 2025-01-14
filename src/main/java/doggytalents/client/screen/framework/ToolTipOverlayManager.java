package doggytalents.client.screen.framework;

import java.util.List;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ToolTipOverlayManager {

    private static ToolTipOverlayManager INSTANCE;

    private List<Component> toolTipComponents;
    private boolean hasToolTip;

    private ToolTipOverlayManager() {}

    public void setComponents(List<Component> components) {
        this.hasToolTip = components != null;
        this.toolTipComponents = components;
    }

    public void reset() {
        this.setComponents(null);
    }

    public boolean hasToolTip() {
        return hasToolTip;
    }

    public void render(Screen screen, GuiGraphics graphics, int mouseX, int mouseY) {
        if (toolTipComponents == null) return;
        graphics.renderComponentTooltip(screen.getMinecraft().font, toolTipComponents, mouseX, mouseY);
    }

    public static ToolTipOverlayManager get() {
        if (INSTANCE == null) {
            INSTANCE = new ToolTipOverlayManager();
        }
        return INSTANCE;
    }

    public static void finish() {
        INSTANCE = null;
    }
}
