package net.xstopho.resource_backpacks.mixin.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.xstopho.resource_backpacks.util.BackpackUtils;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KeyMapping.class)
public abstract class KeyMappingMixin implements BackpackUtils.KeyMappingAccess {

    @Override
    public InputConstants.Key getKey() {
        return ((KeyMappingAccessor) this).backpack$getKey();
    }
}
