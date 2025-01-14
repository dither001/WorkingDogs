package doggytalents.client.screen.DogNewInfoScreen.element.view.MainInfoView;

import doggytalents.client.screen.framework.element.AbstractElement;
import doggytalents.common.entity.Dog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class DogDescriptionViewBoxElement extends AbstractElement {

    private Dog dog;
    private Font font;

    public DogDescriptionViewBoxElement(AbstractElement parent,
        Screen screen, Dog dog) {
        super(parent, screen);
        this.dog = dog;
        var mc = this.getScreen().getMinecraft();
        this.font = mc.font;
    }

    @Override
    public AbstractElement init() {
        return this;
    }

    @Override
    public void renderElement(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        var name = this.dog.getName().getString();
        var age = "" + this.dog.getAge();
        var gender = this.dog.getGenderName().getString();
        var owner = ( this.dog.isOwnedBy(Minecraft.getInstance().player) ?
            I18n.get("doggui.owner.you")
            : this.dog.getOwnersName().orElse(Component.translatable("entity.doggytalents.dog.unknown_owner")).getString()
        );
        var friendlyFire = "" + this.dog.canOwnerAttack();
        var obeyOther = "" + this.dog.willObeyOthers();
        
        int startX = this.getRealX();
        int startY = this.getRealY()
            + ((this.getSizeY() - (6*this.font.lineHeight + 2*5))/2);
        int pY = startY;
        graphics.drawString(font,
            createDescEntry(I18n.get("doggui.home.name") + " ", name, 0xffffffff),
                startX, pY, 0xffffffff);
        pY += this.font.lineHeight + 2;
        graphics.drawString(font,
            createDescEntry(I18n.get("doggui.age") + " ", age, 0xffffffff),
                startX, pY, 0xffffffff);
        pY += this.font.lineHeight + 2;
        graphics.drawString(font,
            createDescEntry(I18n.get("doggui.gender") + " ", gender, 0xffffffff),
                startX, pY, 0xffffffff);
        pY += this.font.lineHeight + 2;
        graphics.drawString(font,
            createDescEntry(I18n.get("doggui.owner") + " ", owner, 0xffffffff),
                startX, pY, 0xffffffff);
        pY += this.font.lineHeight + 2;
        graphics.drawString(font,
            createDescEntry(I18n.get("doggui.friendlyfire") + " ", friendlyFire, 
                this.dog.canOwnerAttack() ? 0xffcda700: 0xffffffff),
            startX, pY, 0xffffffff);
        pY += this.font.lineHeight + 2;
        graphics.drawString(font,
            createDescEntry(I18n.get("doggui.obeyothers") + " ", obeyOther, 
                this.dog.willObeyOthers() ? 0xffcda700: 0xffffffff),
            startX, pY, 0xffffffff);
    }

    private Component createDescEntry(String descName, String descVal, int valColor) {
        var c0 = Component.literal(descName + " ");
        var c1 = Component.literal(descVal)
            .withStyle(
                Style.EMPTY
                .withBold(true)
                .withColor(valColor)
            );
        c0.append(c1);
        return c0;
    }
    
}
