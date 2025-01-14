package doggytalents.client.screen.AmnesiaBoneScreen.screen;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import doggytalents.client.screen.framework.widget.TextOnlyButton;
import doggytalents.client.screen.widget.CustomButton;
import doggytalents.common.entity.Dog;
import doggytalents.common.item.AmnesiaBoneItem;
import doggytalents.common.network.PacketHandler;
import doggytalents.common.network.packet.data.DogMigrateOwnerData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraftforge.network.PacketDistributor;

public class DogMigrateOwnerScreen extends Screen {

    private Dog dog;
    private @Nullable UUID migrateTo;
    private String migrateToStr;
    
    private TextOnlyButton uuidShowButton;

    protected DogMigrateOwnerScreen(Dog dog, @Nullable UUID migrateTo, String ownerName) {
        super(Component.literal(""));
        this.dog = dog;
        this.migrateTo = migrateTo;
        this.migrateToStr = ownerName;
        
    }

    public static void open(Dog dog, UUID migrateTo, String ownerName) {
        var mc = Minecraft.getInstance();
        var screen = new DogMigrateOwnerScreen(dog, migrateTo, ownerName == null ? "" : ownerName);
        mc.setScreen(screen);
    }

    @Override
    protected void init() {

        uuidShowButton = new TextOnlyButton(0, 0, 100, 20, 
        Component.translatable("doggui.migrate_owner.show_uuid")
            .withStyle(ChatFormatting.GRAY), 
            $ -> {}, Minecraft.getInstance().font) {
                @Override
                public void render(GuiGraphics graphics, int mouseX, int mouseY, float pTicks) {
                    super.render(graphics, mouseX, mouseY, pTicks);
                    if (!this.isHovered) return;
                    graphics.renderComponentTooltip(font, List.of(
                        Component.literal(migrateTo == null ? "UUID_ZERO" : migrateTo.toString())
                    ), mouseX, mouseY);
                }
        };

        if (this.migrateTo == null) return;
        
        this.addConfirmButton();
        this.addDenyButton();
        this.addRenderableWidget(this.uuidShowButton);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float pTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, pTicks);

        var stack = graphics.pose();

        if (this.migrateTo != null) {
            this.drawWhenHaveRequest(graphics, mouseX, mouseY, pTicks, this.migrateTo, this.migrateToStr);
            return;
        }

        int mX = this.width/2;
        int mY = this.height/2; 

        int pY = mY - 72;
        Component title;
        title = Component.translatable("doggui.migrate_owner.help.title")
        .withStyle(
            Style.EMPTY
            .withBold(true)
            .withColor(ChatFormatting.RED)
        );
        var help = font.split(Component.translatable("doggui.migrate_owner.help.subtitle"), 300);
        var escToReturn= I18n.get("doggui.invalid_dog.esc_to_return");
        stack.pushPose();
        stack.scale(1.2f, 1.2f, 1.2f);
        graphics.drawString(font, title, Mth.floor(mX/1.2f -font.width(title)/2 ), Mth.floor(pY/1.2f), 0xffffffff);
        stack.popPose();
        pY += 40;
        for (var line : help) {
            graphics.drawString(font, line, mX - font.width(line)/2, pY, 0xffffffff);
            pY += font.lineHeight + 3;
        }
        pY += 40;
        graphics.drawString(font, escToReturn, mX - font.width(escToReturn)/2, pY, 0xffffffff );

    }

    public void drawWhenHaveRequest(GuiGraphics graphics, int mouseX, int mouseY, float pTicks, 
        UUID newOwnerUUID, String newOwnerName) {
        
        var stack = graphics.pose();

        int mX = this.width/2;
        int mY = this.height/2; 

        int pY = mY - 72;
        Component title;
        String help;
        title = Component.translatable("doggui.migrate_owner.confirm.title")
        .withStyle(
            Style.EMPTY
            .withBold(true)
            .withColor(ChatFormatting.RED)
        );
        help = I18n.get(
            "doggui.migrate_owner.confirm.subtitle",
            dog.getName().getString()
        );
        var dog_title = I18n.get(
            "doggui.migrate_owner.info.dog",
            dog.getName().getString()
        );
        var owner_title = Component.translatable(
            "doggui.migrate_owner.info.new_owner",
            newOwnerName
        );
        var costStr = I18n.get("doggui.talents.cost") + AmnesiaBoneItem.getMigrateOwnerXPCost();
        var escToReturn= I18n.get("doggui.invalid_dog.esc_to_return");
        stack.pushPose();
        stack.scale(1.2f, 1.2f, 1.2f);
        graphics.drawString(font, title, Mth.floor(mX/1.2f -font.width(title)/2 ), Mth.floor(pY/1.2f), 0xffffffff);
        stack.popPose();
        pY += 40;
        graphics.drawString(font, help, mX - font.width(help)/2, pY, 0xffffffff);
        pY += 40;
        graphics.drawString(font, dog_title, mX - font.width(dog_title)/2, pY, 0xffffffff );
        pY += font.lineHeight + 3;
        graphics.drawString(font, owner_title, mX - font.width(owner_title)/2, pY, 0xffffffff );
        pY += font.lineHeight + 3;
        this.uuidShowButton.setX(this.width/2 - uuidShowButton.getWidth()/2);
        this.uuidShowButton.setY(pY-6);
        pY += font.lineHeight + 6;  
        graphics.drawString(font, costStr, mX - font.width(costStr)/2, pY, 0xffffffff );
        pY += 40;
        graphics.drawString(font, escToReturn, mX - font.width(escToReturn)/2, pY, 0xffffffff );
    } 

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void addConfirmButton() {
        var confirmButton = new CustomButton(this.width/2 + 2, this.height/2 + 58, 
            50, 20, Component.translatable("doggui.migrate_owner.confirm"), 
            b -> {
                requestMigrateOwner(true);
                Minecraft.getInstance().setScreen(null);
            }
        ) {
            @Override
            public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float pTicks) {
                // TODO Auto-generated method stub
                super.renderWidget(graphics, mouseX, mouseY, pTicks);
                var player = Minecraft.getInstance().player;
                this.active = 
                    (player != null && player.experienceLevel >= AmnesiaBoneItem.getMigrateOwnerXPCost());
            }
            @Override
            public void render(GuiGraphics graphics, int mouseX, int mouseY, float pTicks) {
                super.render(graphics, mouseX, mouseY, pTicks);
                if (!this.isHovered) return;
                MutableComponent c1;
                if (this.active) {
                    return;
                } else {
                    var player = Minecraft.getInstance().player;
                    if (player != null && player.experienceLevel < AmnesiaBoneItem.getMigrateOwnerXPCost()) {
                        c1 = Component.translatable("doggui.detrain.talents.insufficent_xp");
                        c1.setStyle(
                            Style.EMPTY
                            .withColor(0xffB20000)
                            .withBold(true)
                        );
                    } else {
                        return;
                    }
                }
                graphics.renderComponentTooltip(font, List.of(c1), mouseX, mouseY);
            }
        };
        var player = Minecraft.getInstance().player;
        confirmButton.active = 
            (player != null && player.experienceLevel >= AmnesiaBoneItem.getMigrateOwnerXPCost());

        this.addRenderableWidget(confirmButton);
    }

    public void addDenyButton() {
        var denyButton = new CustomButton(this.width/2 - 50 - 2, this.height/2 + 58, 
            50, 20, Component.translatable("doggui.migrate_owner.deny"), 
            b -> {
                requestMigrateOwner(false);
                Minecraft.getInstance().setScreen(null);
            }
        );

        this.addRenderableWidget(denyButton);
    }

    private void requestMigrateOwner(boolean confirmed) {
        PacketHandler.send(PacketDistributor.SERVER.noArg(),
            new DogMigrateOwnerData(dog.getId(), confirmed)
        );
    }
    
    
}
