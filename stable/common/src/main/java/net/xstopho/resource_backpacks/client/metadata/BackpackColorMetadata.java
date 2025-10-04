package net.xstopho.resource_backpacks.client.metadata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.xstopho.resource_backpacks.BackpackConstants;

public record BackpackColorMetadata(String color) {
    public static final Codec<BackpackColorMetadata> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.STRING.fieldOf("color").forGetter(BackpackColorMetadata::color))
                    .apply(instance, BackpackColorMetadata::new));

    public static final MetadataSectionType<BackpackColorMetadata> TYPE = MetadataSectionType.fromCodec(BackpackConstants.MOD_ID, CODEC);

    public int getColor() {
        String hex = this.color().substring(2);
        return Integer.parseUnsignedInt(hex, 16);
    }
}
