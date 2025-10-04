package net.xstopho.resource_backpacks.client.metadata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.xstopho.resource_backpacks.BackpackConstants;

public record BackpackColorMetadata(int color) {
    public static final Codec<BackpackColorMetadata> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("color").forGetter(BackpackColorMetadata::color))
                    .apply(instance, BackpackColorMetadata::new));

    public static final MetadataSectionType<BackpackColorMetadata> TYPE = MetadataSectionType.fromCodec(BackpackConstants.MOD_ID, CODEC);
}
