package doggytalents.client.screen.DogNewInfoScreen.store.slice;

import java.util.List;

import doggytalents.client.DogTextureManager;
import doggytalents.client.entity.skin.DogSkin;
import doggytalents.client.screen.DogNewInfoScreen.store.UIActionTypes;
import doggytalents.client.screen.DogNewInfoScreen.store.payload.InitSkinIndexPayload;
import doggytalents.client.screen.DogNewInfoScreen.store.slice.ActiveTabSlice.Tab;
import doggytalents.client.screen.framework.CleanableSlice;
import doggytalents.client.screen.framework.CommonUIActionTypes;
import doggytalents.client.screen.framework.UIAction;
import doggytalents.common.entity.Dog;
import doggytalents.common.register.DoggyEntityTypes;
import net.minecraft.client.Minecraft;

public class ActiveSkinSlice implements CleanableSlice {

    public static List<DogSkin> locList;
    public static Dog DUMMY_DOG_OBJ;

    public int activeSkinId;
    public boolean showInfo;

    @Override
    public Object getInitalState() {
        var ret = new ActiveSkinSlice();
        ret.activeSkinId = 0;
        ret.showInfo = false;
        return ret;
    }

    @Override
    public Object reducer(Object oldData, UIAction action) {
        if (locList == null) return oldData;
        if (locList.isEmpty()) return oldData;
        if (oldData instanceof ActiveSkinSlice oldActiveSkin) {
            if (action.type == UIActionTypes.Skins.ACTIVE_INC) {
                var ret = new ActiveSkinSlice();
                    ret.activeSkinId = oldActiveSkin.activeSkinId;
                    ret.showInfo = oldActiveSkin.showInfo;
                    ++ret.activeSkinId;
                    if (ret.activeSkinId >= locList.size()) {
                        ret.activeSkinId = locList.size() - 1;
                    }
                    return ret;
            } else if (action.type == UIActionTypes.Skins.ACTIVE_DEC) {
                var ret = new ActiveSkinSlice();
                    ret.activeSkinId = oldActiveSkin.activeSkinId;
                    ret.showInfo = oldActiveSkin.showInfo;
                    --ret.activeSkinId;
                    if (ret.activeSkinId < 0) {
                        ret.activeSkinId = 0;
                    }
                    return ret;  
            } else if (action.type == CommonUIActionTypes.CHANGE_TAB
                || action.type == CommonUIActionTypes.SWITCH_TAB) {
                if (action.payload instanceof InitSkinIndexPayload initSkin && initSkin.getTab() == Tab.STYLE) {
                    return initSkin.getInitSkinIndex();
                }
            } else if (action.type == UIActionTypes.Skins.SHOW_INFO) {
                var ret = new ActiveSkinSlice();
                ret.activeSkinId = oldActiveSkin.activeSkinId;
                ret.showInfo = true;
                return ret;
            } else if (action.type == UIActionTypes.Skins.HIDE_INFO) {
                var ret = new ActiveSkinSlice();
                ret.activeSkinId = oldActiveSkin.activeSkinId;
                ret.showInfo = false;
                return ret;
            }
        }
        return oldData;
    }

    public static void initLocList() {
        locList = DogTextureManager.INSTANCE.getAll();
        setupDummyDog();
    }

    @Override
    public void cleanUpSlice() {
        locList = null;
        DUMMY_DOG_OBJ = null;
    }

    public static void setupDummyDog() {
        if (DUMMY_DOG_OBJ != null) return;
        var level = Minecraft.getInstance().level;
        var dog = DoggyEntityTypes.DOG.get().create(level);
        DUMMY_DOG_OBJ = dog;
    }
    
}
