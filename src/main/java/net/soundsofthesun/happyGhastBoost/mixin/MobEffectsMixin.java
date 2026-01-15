package net.soundsofthesun.happyGhastBoost.mixin;

import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Objects;

@Mixin(MobEffects.class)
public class MobEffectsMixin {

    @ModifyArgs(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;registerForHolder(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/Identifier;Ljava/lang/Object;)Lnet/minecraft/core/Holder$Reference;"))
    private static void modRegisterSpeed(Args args) {
        // Makes speed also increase flight speed, for all entities
        if (Objects.equals(args.get(1), Identifier.withDefaultNamespace("speed"))) {
            args.set(2,
                    new MobEffect(MobEffectCategory.BENEFICIAL, 3402751)
                            .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.withDefaultNamespace("effect.speed"), 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                            .addAttributeModifier(Attributes.FLYING_SPEED, Identifier.withDefaultNamespace("effect.speed"), 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    );
        }
    }
}
