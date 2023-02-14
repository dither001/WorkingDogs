package doggytalents.client.screen.DogNewInfoScreen.element.view.MainInfoView;

import com.mojang.blaze3d.vertex.PoseStack;

import doggytalents.client.screen.DogNewInfoScreen.element.AbstractElement;
import doggytalents.client.screen.DogNewInfoScreen.element.DivElement;
import doggytalents.client.screen.DogNewInfoScreen.element.MainButtonToolboxRowElement;
import doggytalents.client.screen.DogNewInfoScreen.element.ElementPosition.PosType;
import doggytalents.client.screen.DogNewInfoScreen.element.view.MainInfoView.view.EditInfoView;
import doggytalents.client.screen.DogNewInfoScreen.store.Store;
import doggytalents.client.screen.DogNewInfoScreen.store.slice.MainPanelSlice;
import doggytalents.client.screen.DogNewInfoScreen.store.slice.MainPanelSlice.MainTab;
import doggytalents.common.entity.Dog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;

public class MainInfoView extends AbstractElement {

    Dog dog;
    Font font;

    public MainInfoView(AbstractElement parent, Screen screen, Dog dog) {
        super(parent, screen);
        this.dog = dog;
        this.font = Minecraft.getInstance().font;
    }

    @Override
    public AbstractElement init() {
        
        var tab = Store.get(getScreen())
            .getStateOrDefault(MainPanelSlice.class, 
            MainTab.class, MainTab.MAIN);
        
        switch (tab) {
            case EDIT_INFO:
                setupPanelView(tab);
                break;
            default:
                setupMainView();
                break;
            
        }

        return this;
    }

    private void setupMainView() {
        int mX = this.getScreen().width/2;
        int mY = this.getScreen().height/2;
        this.addChildren(
            new DogStatusViewBoxElement(this, this.getScreen(), this.dog)
            .setPosition(PosType.FIXED, mX - 105 - 10, mY - 105/2)
            .setSize(105)
            //.setBackgroundColor(0xffe39c02)
        );
        this.addChildren(
            new DogDescriptionViewBoxElement(this, this.getScreen(), this.dog)
            .setPosition(PosType.FIXED, mX + 10, mY - 105/2)
            .setSize(105)
            //.setBackgroundColor(0xff00ffae)
        );
        this.addChildren(
            new MainButtonToolboxRowElement(this, this.getScreen(), this.dog)
            .setPosition(PosType.FIXED, mX - 50, mY+65)
            .setSize(100, 20)
            //.setBackgroundColor(0xff36888a)
            .init()
        );
    }

    private void setupPanelView(MainTab tab) {
        int sizeX = this.getSizeX();
        int sizeY = this.getSizeY();

        int mX = sizeX/2;
        int mY = sizeY/2;

        int styleViewBoxSizeX = sizeX > 507 ? 448 : sizeX;
        int styleViewBoxSizeY = sizeY > 304 ? 320 : sizeY;
        
        var styleViewBoxDiv = new DivElement(this, getScreen())
            .setPosition(PosType.ABSOLUTE, mX - styleViewBoxSizeX/2, 
            mY - styleViewBoxSizeY/2)
            .setSize(styleViewBoxSizeX, styleViewBoxSizeY);
            //.setBackgroundColor(0xffff05de);
        this.addChildren(styleViewBoxDiv);

        var styleListDiv = new MainViewListPanel(styleViewBoxDiv, getScreen())
            .setPosition(PosType.RELATIVE, 0, 0)
            .setSize(120, 1f)
            .setBackgroundColor(0x87363636)
            .init();

        styleViewBoxDiv.addChildren(styleListDiv);

        AbstractElement rightView;
        switch (tab) {
            default:
                rightView = new EditInfoView(styleViewBoxDiv, getScreen(), dog, font);
                break;
        }
        
        rightView
            .setPosition(PosType.RELATIVE, 0, 0)
            .setSize(styleViewBoxDiv.getSizeX() - 120, 1f)
            .setBackgroundColor(0x57595858)
            .init();
        styleViewBoxDiv.addChildren(rightView);
        
    }

    @Override
    public void renderElement(PoseStack stack, int mouseX, int mouseY, float partialTicks) {

    }
    
}