package doggytalents.client.entity.skin;

import javax.annotation.Nonnull;

import com.google.common.base.Supplier;

import doggytalents.api.inferface.AbstractDog;
import doggytalents.client.entity.model.DogModelRegistry;
import doggytalents.client.entity.model.DogModelRegistry.DogModelHolder;
import doggytalents.client.entity.model.dog.DogModel;
import doggytalents.common.entity.Dog;
import doggytalents.common.lib.Resources;
import net.minecraft.resources.ResourceLocation;

public class DogSkin {

    public static final DogSkin CLASSICAL = new DogSkin(Resources.ENTITY_WOLF).setName("Classical");
    public static final DogSkin MISSING = new DogSkin(Resources.ENTITY_WOLF).setName("<Missing>");

    private String name = "";
    private ResourceLocation texturePath;
    private boolean useCustomModel;
    private DogModelHolder<Dog> customModelHolder;
    private byte tail = 0, ear = 0;

    public DogSkin(ResourceLocation path) {
        this.texturePath = path;
        this.useCustomModel = false;
    }

    public DogSkin(ResourceLocation path, @Nonnull DogModelHolder<Dog> model) {
        this.texturePath = path;
        this.useCustomModel = true;
        this.customModelHolder = model;
    }

    public DogSkin setName(String s) {
        if (s == null) this.name = "";
        else this.name = s;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setTail(byte val) {
        this.tail = val;
    }

    public byte getTail() {
        return this.tail;
    }

    public void setEar(byte val) {
        this.ear = val;
    } 

    public byte getEar() {
        return this.ear;
    }

    public boolean useCustomModel() {
        return this.useCustomModel;
    }

    public DogModelHolder<Dog> getCustomModel() {
        return this.customModelHolder;
    }

    public ResourceLocation getPath() {
        return this.texturePath;
    }

}