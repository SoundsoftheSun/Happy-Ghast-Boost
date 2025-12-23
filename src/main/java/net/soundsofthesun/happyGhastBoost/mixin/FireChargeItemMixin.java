package net.soundsofthesun.happyGhastBoost.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.soundsofthesun.happyGhastBoost.attachments.MAttachments;
import net.soundsofthesun.happyGhastBoost.attachments.SpeedBoostDuration;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FireChargeItem.class)
public abstract class FireChargeItemMixin extends Item implements ProjectileItem {
    public FireChargeItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand usedHand) {
        if (entity.level() instanceof ServerLevel level && entity instanceof HappyGhast ghast) {
            if (!ghast.isBaby()) {
                ghast.addEffect(new MobEffectInstance(MobEffects.SPEED, 20 * (level.getAttachedOrElse(MAttachments.SPEED_BOOST_DURATION, SpeedBoostDuration.DEFAULT)).seconds(), 1));
                level.playSound(ghast, ghast, SoundEvents.GHAST_SHOOT, SoundSource.NEUTRAL, 1F, 1F);
                stack.consume(1, player);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }
}
