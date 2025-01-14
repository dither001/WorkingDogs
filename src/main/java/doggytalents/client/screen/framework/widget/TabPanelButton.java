package doggytalents.client.screen.framework.widget;

import doggytalents.api.registry.Talent;
import doggytalents.client.screen.framework.AbstractSlice;
import doggytalents.client.screen.framework.CommonUIActionTypes;
import doggytalents.client.screen.framework.Store;
import doggytalents.client.screen.framework.UIAction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TabPanelButton extends AbstractButton {
    static final int DEFAULT_COLOR = 0x485e5d5d;
    static final int DEFAULT_HLCOLOR = 0x835e5d5d;
    static final int DEFAULT_SEL_COLOR = 0x487500A5;
    static final int DEFAULT_SEL_HLCOLOR = 0x837500A5;

    Font font;
    Talent talent;
    Screen screen;
    Class<? extends AbstractSlice> slice;
    Object stateValue;
    Boolean selected;

    public TabPanelButton(int x, int y, int width, int height, 
        Screen screen, boolean selected, Component name,
        Class<? extends AbstractSlice> slice, Object stateValue) {
        super(x, y, width, height, name);
        this.font = Minecraft.getInstance().font;
        this.screen = screen;
        this.selected = selected;
        this.slice = slice;
        this.stateValue = stateValue;
    }

    @Override
    public void onPress() {
        Store.get(screen).dispatch(slice, 
            new UIAction(CommonUIActionTypes.CHANGE_PANEL_TAB, stateValue)
        );
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float pTicks) {
        int cl = this.isHovered ? DEFAULT_HLCOLOR : DEFAULT_COLOR;
        int sel_cl = this.isHovered ? DEFAULT_SEL_HLCOLOR : DEFAULT_SEL_COLOR;
        
        graphics.fill( this.getX(), this.getY(), this.getX()+this.width, this.getY()+this.height,
            this.selected ? sel_cl : cl);
        
        //draw text
        int mX = this.getX() + this.width/2;
        int mY = this.getY() + this.height/2;
        var msg = this.getMessage();
        int tX = mX - font.width(msg)/2;
        int tY = mY - font.lineHeight/2;
        //TODO if the name is too long, draw it cut off with a ..
        graphics.drawString(font, msg, tX, tY, 0xffffffff);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput p_259858_) {
        // TODO Auto-generated method stub
        
    }
}
