package doggytalents.client.screen.DogNewInfoScreen.element;

import java.util.ArrayList;

import doggytalents.client.screen.DogNewInfoScreen.store.slice.ActiveTabSlice;
import doggytalents.client.screen.DogNewInfoScreen.widget.NavBarButton;
import doggytalents.client.screen.framework.element.AbstractElement;
import doggytalents.common.entity.Dog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class DogInfoNavBarElement extends AbstractElement {

    private static final int BUTTON_SPACING = 10;
    private Dog dog;

    public DogInfoNavBarElement(AbstractElement parent, Screen screen, Dog dog) {
        super(parent, screen);
        this.dog = dog;
    } 

    @Override
    public AbstractElement init() {
        
        var tabsVal = ActiveTabSlice.Tab.values();
        var bLs = new ArrayList<NavBarButton>();
        var mc = Minecraft.getInstance();
        if (mc == null) return this;
        var font = mc.font;
        int totalButtonWidth = 0;
        
        for (var tab : tabsVal) {
            var title = Component.translatable(tab.unlocalizedTitle);
            int buttonWidth = font.width(title);
            var activeTab = 
                getStateAndSubscribesTo(
                    ActiveTabSlice.class,
                    ActiveTabSlice.Tab.class, ActiveTabSlice.Tab.HOME);
            if (tab == activeTab) {
                title.withStyle(
                    Style.EMPTY
                    .withColor(0xa206c9)
                );
            }
            var button = new NavBarButton(
                0, this.getRealY(), 
                title, 
                tab,
                font, getScreen(), dog
            );
            bLs.add(button);
            totalButtonWidth += buttonWidth;
        }
        var totalWidth = (tabsVal.length-1)*BUTTON_SPACING + totalButtonWidth;
        //int startRelativeXOff = (this.getSizeX() - totalWidth)/2;
        int pX = this.getRealX() - totalWidth/2;
        for (var b : bLs) {
            b.setX(pX);
            this.addChildren(b);
            pX += b.getWidth() + BUTTON_SPACING;
        }
        return this;
    }


    @Override
    public void renderElement(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
    }


}
