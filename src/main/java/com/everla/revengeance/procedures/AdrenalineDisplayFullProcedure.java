package com.everla.revengeance.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.everla.revengeance.init.RevengeanceModAttributes;

public class AdrenalineDisplayFullProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(RevengeanceModAttributes.ADRENALINE_LEVEL) ? _livingEntity0.getAttribute(RevengeanceModAttributes.ADRENALINE_LEVEL).getValue() : 0) >= 10000) {
			return true;
		}
		return false;
	}
}
