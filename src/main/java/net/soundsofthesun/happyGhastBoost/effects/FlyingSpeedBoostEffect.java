package net.soundsofthesun.happyGhastBoost.effects;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.soundsofthesun.happyGhastBoost.HappyGhastBoost;
import org.jetbrains.annotations.Nullable;

public class FlyingSpeedBoostEffect extends MobEffect {
    public FlyingSpeedBoostEffect() {
        super(MobEffectCategory.BENEFICIAL, -1);
    }

    private static final Identifier FLYING_SPEED_MODIFIER = Identifier.fromNamespaceAndPath(HappyGhastBoost.MOD_ID, "flying_speed_modifier");

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        int mod = amplifier+1;
        AttributeInstance flying = entity.getAttribute(Attributes.FLYING_SPEED);
        if (flying != null) flying.addOrReplacePermanentModifier(new AttributeModifier(FLYING_SPEED_MODIFIER, (double) 0.2F*mod, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        AttributeInstance flying = entity.getAttribute(Attributes.FLYING_SPEED);
        if (flying != null) flying.removeModifier(FLYING_SPEED_MODIFIER);
        return flying != null;
    }

    @Override
    public void applyInstantenousEffect(
            ServerLevel level, @Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entity, int amplifier, double health
    ) {
        this.applyEffectTick(level, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration == 1;
    }

}
