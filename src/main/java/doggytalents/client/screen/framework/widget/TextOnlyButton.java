package doggytalents.client.screen.framework.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class TextOnlyButton extends AbstractButton {

    protected final TextOnlyButton.OnPress onPress;
    private Font font;

    public TextOnlyButton(int x, int y, int width, int height, Component comp, 
        TextOnlyButton.OnPress onPress, Font font) {
        super(x, y, width, height, comp);
        this.onPress = onPress;
        this.font = font;
    }

    

    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float pticks) {
        // No HightLight
        if (!this.active) return;
        int mX = this.width/2;
        int mY = this.height/2;
        var txt = this.getMessage();
        int tX = this.getX() + mX - font.width(txt)/2;
        int tY = this.getY() + mY - font.lineHeight/2;
        if (this.isHovered) {
            var txt1 = txt.copy();
            txt1.withStyle(txt1.getStyle().withUnderlined(true).withBold(true));
            tX = this.getX() + mX - font.width(txt1)/2;
            graphics.drawString(font, txt1, tX, tY, 0xffffffff);
        } else {
            graphics.drawString(font, txt, tX, tY, 0xffffffff);
        }

    }

    public interface OnPress {
        void onPress(TextOnlyButton p_93751_);
    }


    @Override
    protected void updateWidgetNarration(NarrationElementOutput p_259858_) {
        // TODO Auto-generated method stub
        
    }
    
}
