package doggytalents.client.screen.DogNewInfoScreen.element;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import doggytalents.client.screen.DogNewInfoScreen.element.ElementPosition.ChildDirection;
import doggytalents.client.screen.DogNewInfoScreen.element.ElementPosition.PosType;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Mth;

public class ScrollView extends AbstractElement {

    private static final double SCROLL_SPEED = 10;
    private static final int SCROLL_BAR_MARGIN_HORZ = 6;
    private static final int SCROLL_BAR_MARGIN_RIGHT = 4;
    private static final int SCROLL_BAR_THICK = 3;
    private static final int SCROLL_BAR_HANDLE_CLR = 0xffd7d9d7;
    private static final int SCROLL_BAR_REST_CLR = 0x90d7d9d7;
    
    //TODO turn this into proper millis!
    long scrollBarAppearDuration = 0;

    ScrollContentContainer container;

    public ScrollView(AbstractElement parent, Screen screen) {
        super(parent, screen);
    }

    @Override
    public AbstractElement init() {
        container = new ScrollContentContainer(this, getScreen());
        container.init();
        this.addChildren(container);
        scrollBarAppearDuration = 0;
        return this;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        GuiComponent.enableScissor(
            this.getRealX(), this.getRealY(), 
            this.getRealX() + this.getSizeX(), 
            this.getRealY() + this.getSizeY());
        super.render(stack, mouseX, mouseY, partialTicks);

        if (scrollBarAppearDuration > 0) {
            --scrollBarAppearDuration;
            drawScrollBar(stack, mouseX, mouseY, partialTicks);
        }
        GuiComponent.disableScissor();
    }

    private void drawScrollBar(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        int fullBarSize = this.getSizeY() - 2*SCROLL_BAR_MARGIN_HORZ;
        int containerSize = this.container.getSizeY();
        if (containerSize <= 0) return;
        if (containerSize <= this.getSizeY()) return;
        float viewportToFullRatio = (float)this.getSizeY() /(float) containerSize;
        int handleSize = Mth.floor(viewportToFullRatio * fullBarSize);
        int maxHandleOffset = fullBarSize - handleSize;
        int handleOffset = Mth.floor(this.getPercentScrolled()* maxHandleOffset);
        
        int barX = this.getRealX() + this.getSizeX() - SCROLL_BAR_MARGIN_RIGHT - SCROLL_BAR_THICK;
        int barY = this.getRealY() + SCROLL_BAR_MARGIN_HORZ;
        fill(stack, barX, barY, barX + SCROLL_BAR_THICK, barY + fullBarSize, SCROLL_BAR_REST_CLR);

        int handleX = barX;
        int handleY = barY + handleOffset;
        fill(stack, handleX, handleY, handleX + SCROLL_BAR_THICK, handleY + handleSize, SCROLL_BAR_HANDLE_CLR);
    }

    //dir : -1.0 = down; 1.0 = up
    @Override
    public boolean mouseScrolled(double x, double y, double dir) {
        scrollBarAppearDuration = 150;
        container.setOffset(Mth.ceil(container.getOffset() + (-dir) * SCROLL_SPEED));
        int maxOff = getMaxOffset();
        if (container.getOffset() < 0) 
            container.setOffset(0);
        else if (container.getOffset() >= maxOff)
            container.setOffset(maxOff);
        return super.mouseScrolled(x, y, dir);
    }

    @Override
    public void renderElement(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
    }

    public void addScrollChildren(AbstractElement element) {
        this.container.addChildren(element);
    }

    public int getMaxOffset() {
        return container.getSizeY() - this.getSizeY();
    }

    public float getPercentScrolled() {
        int maxOff = getMaxOffset();
        if (maxOff <= 0) return 1f;
        return (float) this.container.getPosition().getScrollYOffset() / (float) maxOff; 
    }

    public AbstractElement getContainer() {
        return this.container;
    }

    private static class ScrollContentContainer extends AbstractElement {
        
        public ScrollContentContainer(AbstractElement parent, Screen screen) {
            super(parent, screen);
            //TODO Auto-generated constructor stub
        }

        @Override
        public AbstractElement init() {
            this.setPosition(PosType.SCROLL_ABSOLUTE, 0, 0)
                .setSizeDynamicY(getParent().getSizeX())
                .getPosition().setChildDirection(ChildDirection.COL);
            return this;
        }

        @Override
        public void renderElement(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        }

        public int getOffset() {
            return this.getPosition().getScrollYOffset();
        }

        public void setOffset(int off) {
            this.getPosition().setScrollYOffset(off);
        } 

    }
    
}