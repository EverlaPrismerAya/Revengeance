package com.jsdtqhj.revengeance.potion;

import com.jsdtqhj.revengeance.RevengeanceMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class RageMobEffect extends MobEffect {
	public RageMobEffect() {

		super(MobEffectCategory.BENEFICIAL, -3407872);
		boolean isConfluenceLoaded = ModList.get().isLoaded("confluence");

		Holder<Attribute> attrMagic = isConfluenceLoaded
				? BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.fromNamespaceAndPath("terra_curio", "generic.magic_damage")).orElse(null)
				: null;
		Holder<Attribute> attrRanged = isConfluenceLoaded
				? BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.fromNamespaceAndPath("terra_curio", "generic.ranged_damage")).orElse(null)
				: null;
		Holder<Attribute> attrSummon = isConfluenceLoaded
				? BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.fromNamespaceAndPath("terra_entity", "player.summon_damage")).orElse(null)
				: null;


		this.addAttributeModifier(
				Attributes.ATTACK_DAMAGE,
				ResourceLocation.fromNamespaceAndPath(RevengeanceMod.MODID,"effect.rage.melee"),
				0.35, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);


		if (isConfluenceLoaded && attrMagic != null) {
			this.addAttributeModifier(
					attrMagic,
					ResourceLocation.fromNamespaceAndPath(RevengeanceMod.MODID, "effect.rage.magic"),
					1.16,
					AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
			);
		}
		if (isConfluenceLoaded && attrRanged != null) {
			this.addAttributeModifier(
					attrRanged,
					ResourceLocation.fromNamespaceAndPath(RevengeanceMod.MODID, "effect.rage.ranged"),
					1.16,
					AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
			);
		}
		if (isConfluenceLoaded && attrSummon != null) {
			this.addAttributeModifier(
					attrSummon,
					ResourceLocation.fromNamespaceAndPath(RevengeanceMod.MODID, "effect.rage.summon"),
					0.35,
					AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
			);
		}
	}
}

