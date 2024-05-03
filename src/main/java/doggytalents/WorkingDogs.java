package doggytalents;

import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import doggytalents.api.feature.FoodHandler;
import doggytalents.client.ClientSetup;
import doggytalents.client.DoggyKeybinds;
import doggytalents.client.data.DTBlockstateProvider;
import doggytalents.client.data.DTItemModelProvider;
import doggytalents.client.entity.render.world.BedFinderRenderer;
import doggytalents.client.entity.render.world.CanineTrackerLocateRenderer;
import doggytalents.client.event.ClientEventHandler;
import doggytalents.common.block.tileentity.RiceMillBlockEntity;
import doggytalents.common.chunk.GarbageChunkCollector;
import doggytalents.common.command.DoggyCommands;
import doggytalents.common.config.ConfigHandler;
import doggytalents.common.data.DTAdvancementProvider;
import doggytalents.common.data.DTBlockTagsProvider;
import doggytalents.common.data.DTEntityTagsProvider;
import doggytalents.common.data.DTItemTagsProvider;
import doggytalents.common.data.DTLootModifierProvider;
import doggytalents.common.data.DTLootTableProvider;
import doggytalents.common.data.DTRecipeProvider;
import doggytalents.common.entity.BoostingFoodHandler;
import doggytalents.common.entity.Dog;
import doggytalents.common.entity.DogDrinkMilkHandler;
import doggytalents.common.entity.MeatFoodHandler;
import doggytalents.common.entity.WhitelistFoodHandler;
import doggytalents.common.event.EventHandler;
import doggytalents.common.lib.Constants;
import doggytalents.common.network.PacketHandler;
import doggytalents.common.talent.HappyEaterTalent;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(Constants.MOD_ID)
public class WorkingDogs {

	public static final Logger LOGGER = LogManager.getLogger(Constants.MOD_ID);

	public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(Constants.CHANNEL_NAME)
			.clientAcceptedVersions(Constants.PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(Constants.PROTOCOL_VERSION::equals)
			.networkProtocolVersion(Constants.PROTOCOL_VERSION::toString).simpleChannel();

	public WorkingDogs() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		/* Life cycle */
		modEventBus.addListener(this::gatherData);
		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::interModProcess);

		/* Register */
		DoggyBlocks.BLOCKS.register(modEventBus);
		DoggyTileEntityTypes.TILE_ENTITIES.register(modEventBus);
		DoggyItems.ITEMS.register(modEventBus);
		DoggyEntityTypes.ENTITIES.register(modEventBus);
		DoggyContainerTypes.CONTAINERS.register(modEventBus);
		DoggySerializers.SERIALIZERS.register(modEventBus);
		DoggySounds.SOUNDS.register(modEventBus);
		DoggyRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
		DoggyTalents.TALENTS.register(modEventBus);
		DoggyAccessories.ACCESSORIES.register(modEventBus);
		DoggyAccessoryTypes.ACCESSORY_TYPES.register(modEventBus);
		DoggyAttributes.ATTRIBUTES.register(modEventBus);
		DoggyItemGroups.ITEM_GROUP.register(modEventBus);
		DoggyEffects.EFFECTS.register(modEventBus);

		DTLootModifierProvider.CODEC.register(modEventBus);

		modEventBus.addListener(DoggyRegistries::newRegistry);
		modEventBus.addListener(DoggyEntityTypes::addEntityAttributes);

		final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		forgeEventBus.addListener(this::serverStarting);
		forgeEventBus.addListener(this::registerCommands);

		forgeEventBus.register(new EventHandler());

		/* Client Events */
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(DoggyKeybinds::registerDTKeyMapping);
			modEventBus.addListener(this::clientSetup);
			modEventBus.addListener(DoggyBlocks::registerBlockColours);
			modEventBus.addListener(DoggyItems::registerItemColours);
			modEventBus.addListener(ClientEventHandler::registerModelForBaking);
			modEventBus.addListener(ClientEventHandler::modifyBakedModels);
			modEventBus.addListener(ClientSetup::setupTileEntityRenderers);
			modEventBus.addListener(ClientSetup::setupEntityRenderers);
			modEventBus.addListener(ClientSetup::addClientReloadListeners);
			modEventBus.addListener(ClientSetup::registerOverlay);
			forgeEventBus.register(new ClientEventHandler());
			forgeEventBus.addListener(BedFinderRenderer::onWorldRenderLast);
			forgeEventBus.addListener(CanineTrackerLocateRenderer::onWorldRenderLast);
			forgeEventBus.addListener(CanineTrackerLocateRenderer::tickUpdate);
		});

		ConfigHandler.init(modEventBus);
	}

	private @OnlyIn(Dist.CLIENT) void clientSetup(final FMLClientSetupEvent event) {
		ClientSetup.setupScreenManagers(event);
		ClientSetup.setupCollarRenderers(event);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		PacketHandler.init();
		FoodHandler.registerHandler(new MeatFoodHandler());
		FoodHandler.registerHandler(new BoostingFoodHandler());
		FoodHandler.registerHandler(new DogDrinkMilkHandler());
		FoodHandler.registerHandler(new WhitelistFoodHandler());

		FoodHandler.registerDynPredicate(HappyEaterTalent.INNER_DYN_PRED);
		event.enqueueWork(() -> {
			Dog.initDataParameters();
			DoggyAdvancementTriggers.registerAll();
			DoggyBrewingRecipes.registerAll();
			ConfigHandler.initTalentConfig();
			GarbageChunkCollector.init();
			RiceMillBlockEntity.initGrindMap();
		});
	}

	private void gatherData(final GatherDataEvent event) {
		final DataGenerator gen = event.getGenerator();
		PackOutput packOutput = gen.getPackOutput();
		CompletableFuture<Provider> lookup = event.getLookupProvider();

		if (event.includeClient()) {
			DTBlockstateProvider blockstates = new DTBlockstateProvider(packOutput, event.getExistingFileHelper());
			gen.addProvider(true, blockstates);
			gen.addProvider(true, new DTItemModelProvider(packOutput, blockstates.getExistingHelper()));
		}

		if (event.includeServer()) {
			// gen.addProvider(new DTBlockTagsProvider(gen));
			gen.addProvider(true, new DTAdvancementProvider(packOutput, lookup, event.getExistingFileHelper()));

			DTBlockTagsProvider blockTagProvider = new DTBlockTagsProvider(packOutput, lookup,
					event.getExistingFileHelper());
			gen.addProvider(true, blockTagProvider);
			gen.addProvider(true, new DTItemTagsProvider(packOutput, lookup, blockTagProvider.contentsGetter(),
					event.getExistingFileHelper()));
			gen.addProvider(true, new DTRecipeProvider(packOutput));
			gen.addProvider(true, new DTLootTableProvider(packOutput));
			gen.addProvider(true, new DTLootModifierProvider(packOutput));
			gen.addProvider(true, new DTEntityTagsProvider(packOutput, lookup, event.getExistingFileHelper()));
		}
	}

	private void interModProcess(final InterModProcessEvent event) {
		/* NO OP */
	}

	private void registerCommands(final RegisterCommandsEvent event) {
		DoggyCommands.register(event.getDispatcher());
	}

	private void serverStarting(final ServerStartingEvent event) {
		/* NO OP */
	}

}
