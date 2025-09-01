package com.jsdtqhj.revengeance.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.ResourceLocation;

public class AdrenalineMobEffect extends MobEffect {
	public AdrenalineMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16711783);
		this.addAttributeModifier(
				Attributes.ATTACK_DAMAGE,
				ResourceLocation.parse("revengeance:adrenaline.melee"),
				2.1,
				AttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);
	}

}