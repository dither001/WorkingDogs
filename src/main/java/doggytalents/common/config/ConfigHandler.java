package doggytalents.common.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import doggytalents.api.DoggyTalentsAPI;
import doggytalents.api.registry.Talent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigHandler {

    public static ClientConfig CLIENT;
    public static ServerConfig SERVER;
    public static TalentConfig TALENT;
    public static RespawnTagConfig RESPAWN_TAGS;
    private static ForgeConfigSpec CONFIG_SERVER_SPEC;
    private static ForgeConfigSpec CONFIG_CLIENT_SPEC;
    private static ForgeConfigSpec CONFIG_TALENT_SPEC;
    private static ForgeConfigSpec CONFIG_RESPAWN_TAG_SPEC;

    public static final boolean ALWAYS_SHOW_DOG_NAME = true;
    public static final float DEFAULT_MAX_HUNGER = 120F;
    public static final boolean SEND_SKIN = false;
    public static final boolean DISPLAY_OTHER_DOG_SKINS = false;
    public static final boolean WHISTLE_SOUNDS = true;

    public static void init(IEventBus modEventBus) {
        Pair<ServerConfig, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        CONFIG_SERVER_SPEC = commonPair.getRight();
        SERVER = commonPair.getLeft();
        Pair<ClientConfig, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CONFIG_CLIENT_SPEC = clientPair.getRight();
        CLIENT = clientPair.getLeft();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CONFIG_SERVER_SPEC);
ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CONFIG_CLIENT_SPEC);
        initRespawnTagsConfig();
    }

    public static void initTalentConfig() {
        Pair<TalentConfig, ForgeConfigSpec> talentPair = new ForgeConfigSpec.Builder().configure(TalentConfig::new);
        CONFIG_TALENT_SPEC = talentPair.getRight();
        TALENT = talentPair.getLeft();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CONFIG_TALENT_SPEC, "doggytalents-talents.toml");
    }

    public static void initRespawnTagsConfig() {
        var respawnPair = new ForgeConfigSpec.Builder().configure(RespawnTagConfig::new);
        CONFIG_RESPAWN_TAG_SPEC = respawnPair.getRight();
        RESPAWN_TAGS = respawnPair.getLeft();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CONFIG_RESPAWN_TAG_SPEC, "doggytalents-respawn_tags_to_remove.toml");
    }

    public static class ClientConfig {

        public ForgeConfigSpec.BooleanValue KAMI_PARTICLES;
        public ForgeConfigSpec.BooleanValue RENDER_CHEST;
        public ForgeConfigSpec.BooleanValue ALWAYS_RENDER_CLASSICAL;
        public ForgeConfigSpec.BooleanValue RENDER_INCAPACITATED_TEXTURE;
        public ForgeConfigSpec.BooleanValue RENDER_HEALTH_IN_NAME;
        public ConfigValue<String> DOG_INFO_SEPERATOR;
        public ConfigValue<String> DOG_INFO_HUNGER_FORMAT;
        public ForgeConfigSpec.BooleanValue DOG_INV_BUTTON_IN_INV;
        public ForgeConfigSpec.BooleanValue RENDER_INCAP_TXT_LESS_GRAPHIC;
        public ForgeConfigSpec.BooleanValue RENDER_DIFFOWNER_NAME_DIFFERENT;
        public ForgeConfigSpec.BooleanValue DONT_RENDER_DIFFOWNER_NAME;
        public ForgeConfigSpec.BooleanValue ALWAYS_RENDER_DOG_NAME;
        public ForgeConfigSpec.BooleanValue BLOCK_THIRD_PARTY_NAMETAG;
        public ForgeConfigSpec.BooleanValue USE_VANILLA_RES_FOR_CLASSICAL;
        public ForgeConfigSpec.BooleanValue WORD_LOAD_ICON;
        public ForgeConfigSpec.BooleanValue RENDER_ARMOR;
        public ForgeConfigSpec.BooleanValue BLOCK_RED_OVERLAY_WHEN_HURT;
        public ForgeConfigSpec.BooleanValue DISPLAY_SMOKE_WHEN_ON_FIRE;
        public ForgeConfigSpec.BooleanValue MOUTH_ITEM_FORCE_RENDER;
        public ForgeConfigSpec.IntValue MAX_ANIMATION_LATENCY_ALLOWED;
        public ForgeConfigSpec.BooleanValue USE_LEGACY_DOG_ARMOR_RENDER;
        public ForgeConfigSpec.BooleanValue USE_PLAYER_HELMET_MODEL_BY_DEFAULT;
        public ForgeConfigSpec.BooleanValue USE_THIRD_PARTY_PLAYER_HELMET_MODEL;
        public ForgeConfigSpec.BooleanValue ENABLE_STARTER_BUNDLE_BY_DEFAULT;

        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("General");

            builder.pop();
            builder.push("Dog Render");

            KAMI_PARTICLES = builder
                    .comment("Enables the particle effect on Full Kami dogs.")
                    .translation("doggytalents.config.client.enable_kami_particles")
                    .define("enable_kami_particles", false);
            RENDER_CHEST = builder
                    .comment("Disable this if you want to force all PackPuppy Dog to not render their Chest.")
                    .translation("doggytalents.config.client.render_chest")
                    .define("render_chest", true);
            ALWAYS_RENDER_CLASSICAL = builder
                    .comment("Enable this if you prefer to have The Classical Skin rendered on all dog regardless.")
                    .translation("doggytalents.config.client.always_render_classical")
                    .define("always_render_classical", false);
            RENDER_INCAPACITATED_TEXTURE = builder
                    .comment("When enabled, Dogs will show injured texture while incapacitated.")
                    .translation("doggytalents.config.client.render_incapacitated_overlay")
                    .define("render_incapacitated_overlay", true);
            RENDER_HEALTH_IN_NAME = builder
                .comment("When sneaking, a part of the dog's name will be rendered with a certain color")
                .comment(", the length of the part is based on the health percentage the dog has left")
                .translation("doggytalents.config.client.render_health_in_name")
                .define("render_health_in_name", true);
            DOG_INFO_SEPERATOR = builder
                .comment("Configure what will be displayed in the middle of")
                .comment("each of the info section (e.g Hunger, Gender, Mode) when rendering ")
                .comment("the Dog Status String located above the main Dog Name String.")
                .translation("doggytalents.config.client.dog_info_seperator")
                .define("dog_info_seperator", " · ");
            DOG_INFO_HUNGER_FORMAT = builder
                .comment("Configure the format that will be used to display hunger value")
                .comment("when rendering the Dog Status String located above the main")
                .comment("Dog Name String.")
                .translation("doggytalents.config.client.dog_info_hunger_format")
                .define("dog_info_hunger_format", "%d");
            DOG_INV_BUTTON_IN_INV = builder
                .comment("Render a universal dog inventory access button in your inventory gui.")
                .comment("If this is turned off, you can still access the button via the dog's")
                .comment("GUI in Talents > Pack Puppy.")
                .translation("doggytalents.config.client.dog_inv_button_in_inv")
                .define("dog_inv_button_in_inv", true);
            RENDER_INCAP_TXT_LESS_GRAPHIC = builder
                .comment("If the default incapacitated texture proof too many graphics.")
                .comment("You can substitute this friendlier texture instead.")
                .translation("doggytalents.config.client.render_incap_txt_less_graphic")
                .define("render_incap_txt_less_graphic", false);
            RENDER_DIFFOWNER_NAME_DIFFERENT = builder
                .comment("Render the dogs' name whose owner is not you with a different color.")
                .translation("doggytalents.config.client.render_diffowner_name_different")
                .define("render_diffowner_name_different", true);
            DONT_RENDER_DIFFOWNER_NAME = builder
                .comment("Do not render dogs' names whose owner is not you.")
                .translation("doggytalents.config.client.render_diffowner_name_dont")
                .define("render_diffowner_name_dont", false);
            ALWAYS_RENDER_DOG_NAME = builder
                .comment("Always render dog nametags regardless even if the player blocks it by F1 mode.")
                .translation("doggytalents.config.client.always_render_dog_name")
                .define("always_render_dog_name", false);
            BLOCK_THIRD_PARTY_NAMETAG = builder
                .comment("Attempt to block third parties from rendering overlays that may conflict")
                .comment("with DT's built-in tag. It is recommended that this be achieved via the target third-party's")
                .comment("configurations if they have the option to disable nametag overlay for certain mobs")
                .comment("(Which they should). This option should only be used as the last resort.")
                .translation("doggytalents.config.client.block_third_party_nametag")
                .define("block_third_party_nametag", false);
            USE_VANILLA_RES_FOR_CLASSICAL = builder
                .comment("By default, a copy of the Classical (Vanilla Wolf Texture) provided by the mod")
                .comment("is used to render The Classical Skin, this is to avoid conflicts with resource packs")
                .comment("like Fresh Animations which are directly using the in-game wolf texture. Turning this on")
                .comment("will make DTN directly reference the in-game texture.")
                .translation("doggytalents.config.client.use_vanilla_res_for_classical")
                .define("use_vanilla_res_for_classical", false);
            WORD_LOAD_ICON = builder
                .comment("Show a fun icon on world loading screen.")
                .translation("doggytalents.config.client.world_load_icon")
                .define("world_load_icon", true);
            RENDER_ARMOR = builder
                .comment("Determine if dogs will render their armor.")
                .translation("doggytalents.config.client.render_armor")
                .define("render_armor", true);
            BLOCK_RED_OVERLAY_WHEN_HURT = builder
                .comment("Determine if dogs still render with a red overlay upon hurt like they usually do.")
                .comment("This can be toggled on if the user prefers to not have the red overlay and relies on the animation ")
                .comment("to indicate hurting.")
                .translation("doggytalents.config.client.block_red_overlay_when_hurt")
                .define("block_red_overlay_when_hurt", false);
            DISPLAY_SMOKE_WHEN_ON_FIRE = builder
                .comment("Display extra smoke when the dog is on fire.")
                .translation("doggytalents.config.client.display_smoke_when_on_fire")
                .define("display_smoke_when_on_fire", true);
            MOUTH_ITEM_FORCE_RENDER = builder
                .comment("By default, dogs will render items in their mouth, regardless of models.")
                .comment("This can be disabled if you prefer not to render it on some")
                .comment("less compatible model.")
                .translation("doggytalents.config.client.mouth_item_force_render")
                .define("mouth_item_force_render", true);
            MAX_ANIMATION_LATENCY_ALLOWED = builder
                .comment("Specify the max latency allowed before force adjusting the animation time")
                .comment("client side to sync with server's animation time. The unit is in ticks.")
                .comment("Provide a non positive integer to disable this. Value from 0 to 7 both inclusive will be defaulted to 7.")
                .translation("doggytalents.config.client.max_animation_latency_allowed")
                .defineInRange("max_animation_latency_allowed", 10, Integer.MIN_VALUE, Integer.MAX_VALUE);
            USE_LEGACY_DOG_ARMOR_RENDER = builder
                .comment("Currently, DTN is utilizing a new system for rendering Doggy Armor's Armor")
                .comment("which allows DTN to directly use any texture which the armor item provided")
                .comment("for the player, thus better replicating third-party armor. Set this to true")
                .comment("if you prefer to use the old system instead.")
                .translation("doggytalents.config.client.use_legacy_dog_armor_render")
                .define("use_legacy_dog_armor_render", false);
            USE_THIRD_PARTY_PLAYER_HELMET_MODEL = builder
                .comment("By default, DTN will try querying models from third parties designed for the player when")
                .comment("rendering helmet on the Dog. Disable this to force DTN to use either vanilla's player model or")
                .comment("DTN provided model designed for Dogs (specified by the below option).")
                .translation("doggytalents.config.client.use_third_party_player_helmet_model")
                .define("use_third_party_player_helmet_model", false);
            USE_PLAYER_HELMET_MODEL_BY_DEFAULT = builder
                .comment("By default, if there is no custom Helmet Armor model for the Helmet Item,")
                .comment("DTN will use its own helmet armor model designed for DTN Dog to")
                .comment("render it on the Dog's head. Enable this to make DTN reuse the player's Helmet Model instead.")
                .translation("doggytalents.config.client.use_player_helmet_model_by_default")
                .define("use_player_helmet_model_by_default", false);
            ENABLE_STARTER_BUNDLE_BY_DEFAULT = builder
                .comment("Enable this to allow every World which is running on this Minecraft")
                .comment("instance's Integrated Server to provide new players with a Starter Bundle")
                .comment("regardless of the world's serverconfig.")
                .translation("doggytalents.config.client.enable_starter_bundle_by_default")
                .define("enable_starter_bundle_by_default", false);
            
            builder.pop();
        }

        public static<T> T getConfig(ConfigValue<T> config) {
                if (CONFIG_CLIENT_SPEC.isLoaded()) {
                        return config.get();
                }
                return config.getDefault();
        }

        
    }

    public static class ServerConfig {

        public ForgeConfigSpec.BooleanValue DISABLE_HUNGER;
        public ForgeConfigSpec.BooleanValue STARTING_ITEMS;
        public ForgeConfigSpec.BooleanValue DISABLE_GENDER;
        public ForgeConfigSpec.BooleanValue PUPS_GET_PARENT_LEVELS;
        public ForgeConfigSpec.BooleanValue IMMORTAL_DOGS;
        public ForgeConfigSpec.BooleanValue PLAY_TAG_WITH_DOG;
        public ForgeConfigSpec.BooleanValue DOG_GREET_OWNER;
        public ForgeConfigSpec.IntValue DOG_GREET_OWNER_LIMIT;
        public ForgeConfigSpec.BooleanValue MAX_CREEPER_SWEEPER_DONT_GROWL;
        public ForgeConfigSpec.BooleanValue ALL_PLAYER_CANNOT_ATTACK_DOG;
        public ForgeConfigSpec.BooleanValue ALL_DOG_BLOCK_PORTAL;
        public ForgeConfigSpec.IntValue MAX_HEEL_LIMIT;
        public ForgeConfigSpec.BooleanValue PREVENT_DOGS_PUSHING_EACH_OTHER;
        public ForgeConfigSpec.DoubleValue HUNGER_MODIFIER;
        public ForgeConfigSpec.BooleanValue DISABLE_PRESERVE_UUID;
        public ForgeConfigSpec.IntValue DUPLICATION_RESOLVE_STRATEGY;
        public ForgeConfigSpec.BooleanValue DISABLE_TRAIN_UNTAMED_WOLF;
        public ForgeConfigSpec.BooleanValue DOG_RESPAWN_INCAPACITATED_WHEN_KILLED;
        public ForgeConfigSpec.BooleanValue MOB_RETRIEVER_ONLY_CARRY_DOG;
        public ForgeConfigSpec.BooleanValue WOLF_MOUNT_PASSENGER_COLLISION;
        public ForgeConfigSpec.BooleanValue CONDUCTING_BONE_CROSS_ORIGIN;
        public ForgeConfigSpec.BooleanValue INCAP_VAL_RESET_WHEN_HURT;
        public ForgeConfigSpec.IntValue TRAIN_WOLF_LIMIT;

        public Map<String, ForgeConfigSpec.BooleanValue> DISABLED_TALENTS;

        public ServerConfig(ForgeConfigSpec.Builder builder) {
            builder.push("General");

            //DEBUG_MODE = builder
            //        .comment("Enables debugging mode, which would output values for the sake of finding issues in the mod.")
            //        .define("debugMode", false);

            builder.pop();
            builder.push("Dog Constants");

            DISABLE_HUNGER = builder
                    .comment("By default, Dogs can starve to Incapacitated if are not fed properly.")
                    .comment("Disable this to prevent this behaviour. Do note that you can still feed")
                    .comment("your Dog to recover health.")
                    .translation("doggytalents.config.dog.disable_hunger")
                    .define("disable_hunger", false);
            STARTING_ITEMS = builder
                    .comment("When enabled, DTN will provide new players of this world")
                    .comment("(relative to the point that this option is first enabled on this world)")
                    .comment("a Starter Bundle which contains certain key items which assist you in starting")
                    .comment("with DTN.")
                    .translation("doggytalents.config.enable_starter_bundle")
                    .define("enable_starter_bundle", false);
            DISABLE_GENDER = builder
                    .comment("By default, each Dog will be one of the Biological Genders. This, like how Biological Genders")
                    .comment("work, add restrictions how Dogs can breed. Therefore, only Dogs whose gender is Biologically Opposed")
                    .comment("can mate with each other. Set this to true to disable this behaviour.")
                    .translation("doggytalents.config.disable_dog_gender")
                    .define("disable_dog_gender", false);
            PUPS_GET_PARENT_LEVELS = builder
                    .comment("When enabled, puppies get some levels from their parents. When disabled, puppies start at 0 points.")
                    .translation("doggytalents.config.enable_pup_get_parent_levels")
                    .define("enable_pup_get_parent_levels", false);
            IMMORTAL_DOGS = builder
                .comment("When enabled, dogs cannot be killed by any means (except creative-mode-bypass damage, in that case, you can still respawn your dog using his linked bed or commands).")
                .comment("Instead, when his health reaches Zero, he will go into Incapacitated Mode.")
                .translation("doggytalents.config.immortal_dogs")
                .define("immortal_dogs", true);
            PLAY_TAG_WITH_DOG = builder
                .comment("When enabled, dog can play tag with you. To make them play, throw a snowball at them.")
                .translation("doggytalents.config.play_tag_with_dog")
                .define("play_tag_with_dog", true);
            DOG_GREET_OWNER = builder
                .comment("When enabled, dogs will start to miss you when you leave them for too long.")
                .comment("And when you come back, they will rush to you and greet you with love!")
                .translation("doggytalents.dog_greet_owner")
                .define("dog_greet_owner", true);
            DOG_GREET_OWNER_LIMIT = builder
                .comment("Specify how many dogs can greet you when you approach more than one missing dog.")
                .comment("The remaning dogs will remain in their position.")
                .comment("To disable the limit, set this to any non-positive integer.")
                .comment("Although this will cause all of your dogs to stand up and greet.")
                .comment("YOU HAVE BEEN WARNED! :)")
                .translation("doggytalents.dog_greet_owner_limit")
                .defineInRange("dog_greet_owner_limit", 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
            MAX_CREEPER_SWEEPER_DONT_GROWL = builder
                .comment("Option to disable dogs who mastered Creeper Sweeper and ")
                .comment("and are able to tackle Creepers. This is helpful if players intend")
                .comment("their Creeper Sweeper master dogs to focus on fighting Creeper instead of")
                .comment("warning you, which may causes a bunch of false positives due to the range")
                .comment("being so wide that it includes Creepers which are not in danger zone or not reachable.")
                .translation("doggytalents.max_creeper_sweeper_dont_growl")
                .define("max_creeper_sweeper_dont_growl", true);
            ALL_PLAYER_CANNOT_ATTACK_DOG = builder
                .comment("Option to disable friendly fire for all players toward all dogs.")
                .comment("This is used to always be the case with friendly fire disabled for a single dog.")
                .translation("doggytalents.all_player_cannot_attack_dog")
                .define("all_player_cannot_attack_dog", false);
            ALL_DOG_BLOCK_PORTAL = builder
                .comment("Option to prevent dogs from accidentally going into a portal and get")
                .comment("unnecessary transferred to another dimension, causing the owner to have")
                .comment("to take them back. The preferred way to make a dog go to another dimension is")
                .comment("to have Cross Origin Teleport set to true on that dog.")
                .translation("doggytalents.all_dog_block_portal")
                .define("all_dog_block_portal", true);
            MAX_HEEL_LIMIT = builder
                .comment("Define how many dogs a player can listen to commands which summon more than one dog to you")
                .comment("at the same time. Set this to any negative value to remove the limit completely.")
                .translation("doggytalents.max_heel_limit")
                .defineInRange("max_heel_limit", 20, Integer.MIN_VALUE, Integer.MAX_VALUE);
            PREVENT_DOGS_PUSHING_EACH_OTHER = builder
                .comment("Prevent dogs from pushing each other when navigating, this prevents dogs from pushing")
                .comment("another dog into a dangerous area and improves navigation.")
                .translation("doggytalents.prevent_dogs_pushing_each_other")
                .define("prevent_dogs_pushing_each_other", true);
            HUNGER_MODIFIER = builder
                .comment("Adjust this value to increase or decrease the Dog's hunger speed to your liking.")
                .comment("ex: Set this to 0.5 to halve the Dog Hunger decrease speed.")
                .translation("doggytalents.hunger_modifier")
                .defineInRange("hunger_modifier", 1D, 0D, 2D);
            DISABLE_PRESERVE_UUID = builder
                .comment("Determine if the UUIDs of the dogs are being kept when training from vanilla")
                .comment("and when they respawn on bed. This also allows Duplication Detection.")
                .translation("doggytalents.preserve_uuid")
                .define("disable_preserve_uuid", false);
            DUPLICATION_RESOLVE_STRATEGY = builder
                .comment("Duplication Detection")
                .comment("Specify what to do when a Duplication is detected when third parties are trying")
                .comment("to load the dog from their own copy.")
                .comment("Option are specified via the following Integer values")
                .comment("0 - Throw an exception out, this may produce a crash. However, this is the recommended way.")
                .comment("1 - Silently remove the dog.")
                .comment("Other - Do nothing.")
                .translation("doggytalents.duplication_resolve_strategy")
                .defineInRange("duplication_resolve_strategy", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            DISABLE_TRAIN_UNTAMED_WOLF = builder
                .comment("By default, you can directly train an untamed wolf to a Dog")
                .comment("with a Training Treat. Set this to True to disable.")
                .translation("doggytalents.train_untamed_wolf")
                .define("disable_train_untamed_wolf", false);
            DOG_RESPAWN_INCAPACITATED_WHEN_KILLED = builder
                .comment("By default, Dogs respawning from bed after being killed will")
                .comment("be incapacitated and are required to be nursed back to life.")
                .comment("Set this to False to disable.")
                .translation("doggytalents.dog_respawn_incapacitated_when_killed")
                .define("dog_respawn_incapacitated_when_killed", true);
            MOB_RETRIEVER_ONLY_CARRY_DOG = builder
                .comment("Enable this if you prefer Mob Retriever to only work with DTN's dog.")
                .translation("doggytalents.mob_retriever_only_carry_dog")
                .define("mob_retriever_only_carry_dog", false);
            WOLF_MOUNT_PASSENGER_COLLISION = builder
                .comment("By default, Dogs extend their bounding box covering the passengers")
                .comment("to avoid suffocating them while calculating the appropriate path.")
                .comment("Set this to false to disable it, but be warned, although Mob Retriever Dogs will still")
                .comment("try to calculate the appropriate path while carrying another mob, there might be accidental suffocations.")
                .translation("doggytalents.wolf_mount_passenger_collision")
                .define("wolf_mount_passenger_collision", true);
            CONDUCTING_BONE_CROSS_ORIGIN = builder
                .comment("By default, Conducting Bone users can summon their Dogs even when they are in")
                .comment("other dimensions. Set this to false to limit Conducting Bone Users to only summon")
                .comment("Dogs of the same dimension.")
                .translation("doggytalents.conducting_bone_cross_origin")
                .define("conducting_bone_cross_origin", true);
            INCAP_VAL_RESET_WHEN_HURT = builder
                .comment("Determine if Dog's incapacitated value should be reset when taking any damage")
                .comment("while being Incapacitated.")
                .translation("doggytalents.incap_val_reset_when_hurt")
                .define("incap_val_reset_when_hurt", true);
            TRAIN_WOLF_LIMIT = builder
                .comment("Set the maximum amount of wolves each players can train to DTN's Dogs.")
                .comment("If a player meet or exceed this cap, he will not be able to train more wolves into DTN's Dogs.")
                .comment("Set this to a value greater than Zero to activate this cap, other value")
                .comment("will disable the cap, meaning players can get unlimited dogs.")
                .translation("doggytalents.train_wolf_limit")
                .defineInRange("train_wolf_limit", -1, Integer.MIN_VALUE, Integer.MAX_VALUE);

            builder.pop();
        }

        public static<T> T getConfig(ConfigValue<T> config) {
                if (CONFIG_SERVER_SPEC.isLoaded()) {
                        return config.get();
                }
                return config.getDefault();
        }
    }

    public static class TalentConfig {
        public Map<Talent, ForgeConfigSpec.BooleanValue> DISABLED_TALENTS;

        public TalentConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Here you can disable talents.")
                .comment("Notice that players with admin privileges can bypass this.").push("Talents");

            DISABLED_TALENTS = new HashMap<>();

            DoggyTalentsAPI.TALENTS.get().forEach((loc) ->
                DISABLED_TALENTS.put(loc, builder.define(DoggyTalentsAPI.TALENTS.get().getKey(loc).toString(), true))
            );
            builder.pop();
        }

        public boolean getFlag(Talent talent) {
            ForgeConfigSpec.BooleanValue booleanValue = this.DISABLED_TALENTS.get(talent);
            if (booleanValue == null) {
                return true;
            }

            return TalentConfig.getConfig(booleanValue);
        }

        public static<T> T getConfig(ConfigValue<T> config) {
                if (CONFIG_TALENT_SPEC.isLoaded()) {
                        return config.get();
                }
                return config.getDefault();
        }
    }

    public static class RespawnTagConfig {

        public ForgeConfigSpec.IntValue STRATEGY;
        public ConfigValue<List<? extends String>> TAGS_TO_REMOVE;
        public ConfigValue<List<? extends String>> TAGS_TO_KEEP;

        public RespawnTagConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Specify the Strategy to be used when picking which data to keep and remove");
            builder.comment("when a Dog got unloaded into DTN Respawn Storage");
            builder.comment("0: Removes all tags, keeping only the Dog's Owner, the Dog's Age, DTN's saved data");
            builder.comment("and additional tags provided by tags_to_keep below.");
            builder.comment("1: Keep all tags, and remove certain tags specified in respawn_removal_tags except");
            builder.comment("important DTN tags.");
            builder.comment("Other: Defaulted to 0");

            STRATEGY = builder.defineInRange("restore_strategy", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            
            TAGS_TO_REMOVE = builder
                .translation("doggytalents.respawn_removal_tags")    
                .<String>defineList("respawn_removal_tags", List.of(), 
                    obj -> {
                        return obj instanceof String;
                    }
                );
            TAGS_TO_KEEP = builder
                .translation("doggytalents.tags_to_keep")    
                .<String>defineList("tags_to_keep", List.of(), 
                    obj -> {
                        return obj instanceof String;
                    }
                );
        }

        public static<T> T getConfig(ConfigValue<T> config) {
            if (CONFIG_RESPAWN_TAG_SPEC.isLoaded()) {
                    return config.get();
            }
            return config.getDefault();
        }

    }
}
