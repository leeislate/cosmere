/*
 * File created ~ 13 - 7 - 2021 ~ Leaf
 */

package leaf.cosmere.datagen.advancements;

import leaf.cosmere.constants.Metals;
import leaf.cosmere.manifestation.AManifestation;
import leaf.cosmere.manifestation.feruchemy.FeruchemyBase;
import leaf.cosmere.registry.ItemsRegistry;
import leaf.cosmere.registry.ManifestationRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.TickTrigger;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;

import java.util.Locale;
import java.util.function.Consumer;

public class FeruchemyAdvancements implements Consumer<Consumer<Advancement>>
{
    public FeruchemyAdvancements()
    {
    }

    public void accept(Consumer<Advancement> advancementConsumer)
    {
        final String categoryName = "feruchemy";

        Advancement root = Advancement.Builder.builder()
                .withDisplay(ItemsRegistry.GUIDE.get(),
                        new TranslationTextComponent("advancements.cosmere." + categoryName + ".title"),
                        new TranslationTextComponent("advancements.cosmere." + categoryName + ".description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        false,
                        false,
                        false)
                .withCriterion("tick", new TickTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND))
                //.withRewards(new AdvancementRewards(0, new ResourceLocation[]{new ResourceLocation("cosmere:guide")}, new ResourceLocation[0], FunctionObject.CacheableFunction.EMPTY))
                .register(advancementConsumer, categoryName + "/root");

        for (RegistryObject<AManifestation> manifestation : ManifestationRegistry.FERUCHEMY_POWERS.values())
        {
            FeruchemyBase feruchemyBase = (FeruchemyBase) manifestation.get();

            Metals.MetalType metalType = feruchemyBase.getMetalType();
            String metalName = metalType.name().toLowerCase(Locale.ROOT);

            Advancement manifestationObtainedAdvancement = Advancement.Builder.builder()
                    .withParent(root)
                    .withDisplay(
                            Items.WOODEN_PICKAXE,
                            new TranslationTextComponent("advancements.cosmere." + categoryName + ".title"),
                            new TranslationTextComponent("advancements.cosmere." + categoryName + ".description"),
                            (ResourceLocation) null,
                            FrameType.TASK,
                            true,
                            true,
                            false)
                    .withCriterion(
                            "get_stone",
                            InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ItemTags.STONE_TOOL_MATERIALS).build()))
                    .register(advancementConsumer, categoryName + "/" + metalName);
        }


    }
}