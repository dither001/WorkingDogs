package doggytalents.client.screen.DogNewInfoScreen.element.view.MainInfoView.dropdown.AddGroupMenu;

import java.util.ArrayList;

import doggytalents.client.screen.framework.element.AbstractElement;
import doggytalents.client.screen.framework.widget.FlatButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class ColorSelectElement extends AbstractElement {

    public static int BUTTON_SIZE = 12;
    public static int BUTTON_SPACING = 2;
    public int selectedColor = COLORS[0];
    public static int PADDING_HORZ = 5;
    private ArrayList<ColorButton> colorButtons;

    private static int[] COLORS = {
        0xfff72323, 0xff32bcfc, 0xff32fca4, 0xffedf037, 0xffb952d9,
        0xffe37036, 0xff69ed45, 0xff2d65d6, 0xff2dd3d6, 0xff8166ed
    };

    public ColorSelectElement(AbstractElement parent, Screen screen) {
        super(parent, screen);
    }

    @Override
    public AbstractElement init() {
        colorButtons = new ArrayList<>(10);

        int aX = this.getRealX() + PADDING_HORZ;
        int pX = aX;
        int pY = this.getRealY();

        int rowSize = Mth.floor(( BUTTON_SPACING - PADDING_HORZ*2 + this.getSizeX() ) 
            /(BUTTON_SIZE + BUTTON_SPACING));
        
        int color_indx = 0;
        while (color_indx < COLORS.length) {
            pX = aX;
            for (int i = 0; i < rowSize; ++i) {
                var button = new ColorButton(
                    pX, pY, COLORS[color_indx], b -> {
                        this.selectedColor = b.getColor();
                    });
                colorButtons.add(button);
                this.addChildren(button);
                pX += BUTTON_SIZE + BUTTON_SPACING;
                ++color_indx;
                if (color_indx >=COLORS.length) break;
            }
            pY += BUTTON_SIZE + BUTTON_SPACING;
        }
        return this;
    }

    @Override
    public void renderElement(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        for (var colorButton : this.colorButtons) {
            colorButton.selected =
                this.selectedColor == colorButton.getColor();
        }
    }


    private static class ColorButton extends FlatButton {

        private int color;
        private OnPress onPress;
        public boolean selected;
        

        public ColorButton(int x, int y, int color, OnPress onPress) {
            super(x, y, BUTTON_SIZE, BUTTON_SIZE, Component.empty(), null);
            this.color = color;
            this.onPress = onPress;
        }

        public int getColor() { return color; }

        public interface OnPress {
            void onPress(ColorButton button);
        }

        @Override
        public void onPress() {
            this.onPress.onPress(this);
        }

        @Override
        public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float pTicks) {
            if (!this.active) return;

            if (selected) {
                graphics.fill( this.getX() - 1, this.getY() - 1, 
                    this.getX()+this.width + 1, this.getY()+this.height + 1, 0xffffffff);
            }
            graphics.fill( this.getX(), this.getY(), this.getX()+this.width, this.getY()+this.height, this.color);
        }
        
    }

    
}
