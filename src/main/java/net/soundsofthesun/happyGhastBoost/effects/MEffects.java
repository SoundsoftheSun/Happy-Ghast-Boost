package net.soundsofthesun.happyGhastBoost.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.soundsofthesun.happyGhastBoost.HappyGhastBoost;

public class MEffects {
    public static final Holder<MobEffect> FLYING_SPEED_EFFECT =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(HappyGhastBoost.MOD_ID, "flying_speed_modifier"), new FlyingSpeedBoostEffect());

    public static void init() {

    }
}
