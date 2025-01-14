package doggytalents.client.screen.DogNewInfoScreen.element.view.TalentView;

import java.util.ArrayList;

import doggytalents.api.registry.Talent;
import doggytalents.client.screen.DogNewInfoScreen.store.slice.TalentListSlice.TalentListData;
import doggytalents.client.screen.DogNewInfoScreen.widget.TalentListEntryButton;
import doggytalents.client.screen.framework.element.AbstractElement;
import doggytalents.common.entity.Dog;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Mth;

public class TalentButtonEntryElement extends AbstractElement {

    Dog dog;
    TalentListData talentList;
    int pageIndex;
    Talent selectedTalent;

    static final int BUTTON_HEIGHT = 20;
    static final int BUTTON_SPACING = 2;
    static final int PADDING_TOP = 0;
    static final int PADDING_LEFT = 0;

    public TalentButtonEntryElement(AbstractElement parent, Screen screen, 
        Dog dog, int pageIndex, TalentListData talentList, Talent selectedTalent) {
        super(parent, screen);
        this.dog = dog;
        this.pageIndex = pageIndex;
        this.talentList = talentList;
        this.selectedTalent = selectedTalent;
    }

    @Override
    public AbstractElement init() {
        int buttonsUntilFull = this.calculateButtonCanFilled(this.getSizeY());
        var talentButtons = new ArrayList<TalentListEntryButton>();
        int startTalentIndex = buttonsUntilFull*(pageIndex-1);
        for (int i = startTalentIndex; i < this.talentList.talents.size(); ++i) {
            var talent = this.talentList.talents.get(i);
            talentButtons.add(
                new TalentListEntryButton(0, 0, 
                    this.getSizeX(), BUTTON_HEIGHT, talent, 
                    getScreen(), dog, talent == selectedTalent)
            );
            --buttonsUntilFull;
            if (buttonsUntilFull <= 0) break;
        }
        int startX = this.getRealX() + PADDING_LEFT;
        int pY = this.getRealY() + PADDING_TOP;
        for (var b : talentButtons) {
            b.setX(startX);
            b.setY(pY);
            pY += b.getHeight() + BUTTON_SPACING;
            this.addChildren(b);
        }

        return this;
    }

    @Override
    public void renderElement(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        
    }

    public int calculateButtonCanFilled(int height) {
        return Mth.floor( Math.max(
            (height - PADDING_TOP) / (BUTTON_SPACING + BUTTON_HEIGHT), 0
        ));
    }

    public int calculateMaxPage(int talentSize) {
        return this.calculateMaxPageInternal(talentSize, calculateButtonCanFilled(this.getSizeY()));
    }

    private int calculateMaxPageInternal(int talentSize, int buttonInOnePage) {
        return Mth.ceil(((float)talentSize)/((float)buttonInOnePage));
    }
    
}
