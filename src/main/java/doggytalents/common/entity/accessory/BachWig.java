package doggytalents.common.entity.accessory;

import java.util.List;
import java.util.function.Supplier;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.api.inferface.IDogAlteration;
import doggytalents.api.registry.Accessory;
import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.render.AccessoryModelManager.Entry;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.AccessoryModelRenderEntries;
import doggytalents.client.entity.render.layer.accessory.modelrenderentry.IAccessoryHasModel;
import doggytalents.common.item.AccessoryItem;
import doggytalents.common.register.DoggyAccessoryTypes;
import doggytalents.common.register.DoggyItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;

public class BachWig extends Accessory implements IAccessoryHasModel {

    public BachWig(Supplier<? extends ItemLike> itemIn) {
        super(DoggyAccessoryTypes.HEAD, itemIn);
    }

    @Override
    public byte getRenderLayer() {
        return AccessoryInstance.RENDER_TOP;
    }

    public AccessoryInstance getDefault() {
        return new Inst(this);
    }

    public static class Inst extends AccessoryInstance implements IDogAlteration {

        public static final List<RegistryObject<Item>> DISC_LIST = List.of(
            DoggyItems.MUSIC_DISC_OKAMI_1,
            DoggyItems.MUSIC_DISC_BWV_1080_FUGUE_11_KIMIKO,
            DoggyItems.MUSIC_DISC_BWV_849_FUGUE_KIMIKO
        );

        public Inst(Accessory typeIn) {
            super(typeIn);
        }

        @Override
        public void doAdditionalAttackEffects(AbstractDog dogIn, Entity target) {
            if (target == null || target.isAlive()) return;
            mayDropDisc(dogIn, target);
        }

        public void mayDropDisc(AbstractDog dog, Entity target) {
            float r = dog.getRandom().nextFloat();
            final float chance = 0.15f;
            if (r > chance) return;
            int indx = dog.getRandom().nextInt(DISC_LIST.size());
            dog.spawnAtLocation(new ItemStack(DISC_LIST.get(indx).get()), 0.0F);
        }
        
    }

    public static class BachWigItem extends AccessoryItem {

        public BachWigItem(Supplier<? extends DyeableAccessory> accessoryIn, Properties properties) {
            super(accessoryIn, properties);
        }

    }

    @Override
    public Entry getRenderEntry() {
        return AccessoryModelRenderEntries.BACH_WIG;
    }
}
