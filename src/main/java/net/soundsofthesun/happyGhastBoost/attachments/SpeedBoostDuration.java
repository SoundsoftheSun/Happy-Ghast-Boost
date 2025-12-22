package net.soundsofthesun.happyGhastBoost.attachments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record SpeedBoostDuration(Integer seconds) {
    public static Codec<SpeedBoostDuration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("seconds").forGetter(SpeedBoostDuration::seconds)
    ).apply(instance, SpeedBoostDuration::new));

    public static StreamCodec<ByteBuf, SpeedBoostDuration> PACKET_CODEC = ByteBufCodecs.fromCodec(CODEC);

    public static SpeedBoostDuration DEFAULT = new SpeedBoostDuration(60);

    public SpeedBoostDuration clear() {
        return DEFAULT;
    }
}
