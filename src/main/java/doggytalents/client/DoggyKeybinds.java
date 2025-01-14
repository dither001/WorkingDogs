package doggytalents.client;

import com.mojang.blaze3d.platform.InputConstants;

import doggytalents.common.register.DoggyItems;
import doggytalents.common.util.InventoryUtil;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;

public class DoggyKeybinds {
    
    public static String CATEGORIES_DT = "key.categories.doggy_talents";
    public static KeyMapping hotkeyWhistle_1 
        = new KeyMapping(
            "key.whistle.1", 
            DTKeyConflictContext.IN_GAME_AND_HAS_WHISTLE, 
            KeyModifier.SHIFT, 
            InputConstants.Type.KEYSYM, 
            InputConstants.KEY_1, CATEGORIES_DT
        );
    public static KeyMapping hotkeyWhistle_2
        = new KeyMapping(
            "key.whistle.2", 
            DTKeyConflictContext.IN_GAME_AND_HAS_WHISTLE, 
            KeyModifier.SHIFT, 
            InputConstants.Type.KEYSYM, 
            InputConstants.KEY_2, CATEGORIES_DT
        );
    public static KeyMapping hotkeyWhistle_3
        = new KeyMapping(
            "key.whistle.3", 
            DTKeyConflictContext.IN_GAME_AND_HAS_WHISTLE, 
            KeyModifier.SHIFT, 
            InputConstants.Type.KEYSYM, 
            InputConstants.KEY_3, CATEGORIES_DT
        );
    public static KeyMapping hotkeyWhistle_4
        = new KeyMapping(
            "key.whistle.4", 
            DTKeyConflictContext.IN_GAME_AND_HAS_WHISTLE, 
            KeyModifier.SHIFT, 
            InputConstants.Type.KEYSYM, 
            InputConstants.KEY_4, CATEGORIES_DT
        );

    public static KeyMapping[] hotkeys_whistle = 
        {hotkeyWhistle_1, hotkeyWhistle_2, hotkeyWhistle_3, hotkeyWhistle_4};

    public static void registerDTKeyMapping(final RegisterKeyMappingsEvent event) {
        event.register(hotkeyWhistle_1);
        event.register(hotkeyWhistle_2);
        event.register(hotkeyWhistle_3);
        event.register(hotkeyWhistle_4);
    }

    public static enum DTKeyConflictContext implements IKeyConflictContext {
        IN_GAME_AND_HAS_WHISTLE {
            @Override
            public boolean isActive() {
                var mc = Minecraft.getInstance();
                var screen = mc.screen;
                if (screen != null) return false;
                var player = mc.player;
                if (player == null) return false;
                var whistle = DoggyItems.WHISTLE.get();
                var whistle_stack = 
                    InventoryUtil.findStackWithItemFromHands(player, whistle);
                if (whistle_stack != null) return true;
                
                return false;
            }
    
            @Override
            public boolean conflicts(IKeyConflictContext other) {
                return this == other;
            }
        }
    }

}
