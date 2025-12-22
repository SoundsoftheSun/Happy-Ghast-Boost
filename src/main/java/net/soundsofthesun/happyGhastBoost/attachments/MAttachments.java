package net.soundsofthesun.happyGhastBoost.attachments;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.happyGhastBoost.HappyGhastBoost;

public class MAttachments {
    public static final AttachmentType<SpeedBoostDuration> SPEED_BOOST_DURATION = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(HappyGhastBoost.MOD_ID, "speed_boost_duration"),
            builder->builder
                    .initializer(()-> SpeedBoostDuration.DEFAULT)
                    .persistent(SpeedBoostDuration.CODEC)
                    .syncWith(
                            SpeedBoostDuration.PACKET_CODEC,
                            AttachmentSyncPredicate.all()
                    )
    );
    public static void init() {}
}
