/*
 * File updated ~ 19 - 11 - 2023 ~ Leaf
 */

package leaf.cosmere.aviar;

import leaf.cosmere.api.helpers.ResourceLocationHelper;
import leaf.cosmere.api.providers.IEntityTypeProvider;
import leaf.cosmere.api.text.StringHelper;
import leaf.cosmere.aviar.common.Aviar;
import leaf.cosmere.aviar.common.registries.AviarEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class AviarEngLangGen extends LanguageProvider
{
	public AviarEngLangGen(DataGenerator gen)
	{
		super(gen, Aviar.MODID, "en_us");
	}

	@Override
	protected void addTranslations()
	{
		addItemsAndBlocks();
		addEntities();
		addAdvancements();
		addManifestations();
		addAttributes();
		addPatchouli();
		addTooltips();
		addItemGroups();
		addDamageSources();
		addMobEffects();
		addCurioIdentifiers();
		addConfigs();
		addCommands();
		addKeybindings();
		addStats();

	}


	private void addItemsAndBlocks()
	{
		//Items and Blocks
		for (Item item : ForgeRegistries.ITEMS.getValues())
		{
			final ResourceLocation registryName = ResourceLocationHelper.get(item);
			if (registryName.getNamespace().contentEquals(Aviar.MODID))
			{
				String localisedString = StringHelper.fixCapitalisation(registryName.getPath());
				add(item.getDescriptionId(), localisedString);
			}
		}
	}

	private void addEntities()
	{
		//Entities
		for (IEntityTypeProvider type : AviarEntityTypes.ENTITY_TYPES.getAllEntityTypes())
		{
			final ResourceLocation id = type.getRegistryName();
			add(type.getEntityType().getDescriptionId(), StringHelper.fixCapitalisation(id.getPath()));
		}
	}

	private void addAdvancements()
	{

	}

	private void addManifestations()
	{
	}

	private void addAttributes()
	{
	}

	private void addPatchouli()
	{

	}

	private void addTooltips()
	{

	}

	private void addItemGroups()
	{
		//ItemGroups/Tabs
		//add("itemGroup." + AviarItemGroups.GROUP.getRecipeFolderName(), "Aviar Group");
	}

	private void addDamageSources()
	{
	}

	private void addMobEffects()
	{
	}

	private void addCurioIdentifiers()
	{
	}

	private void addConfigs()
	{
	}

	private void addCommands()
	{
	}

	private void addKeybindings()
	{
	}

	private void addStats()
	{
	}
}
