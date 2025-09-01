package com.jsdtqhj.revengeance.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.ResourceLocation;

public class RageMobEffect extends MobEffect {
	public RageMobEffect() {

		super(MobEffectCategory.BENEFICIAL, -3407872);
		this.addAttributeModifier(
				Attributes.ATTACK_DAMAGE,
				ResourceLocation.parse("revengeance:rage.melee"),
				1.35, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
	}
}

