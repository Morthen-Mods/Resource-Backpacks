package net.xstopho.resource_backpacks.mixin.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.xstopho.resource_backpacks.client.util.BackpackClientUtils;
import net.xstopho.resource_backpacks.mixin.accessors.KeyMappingAccessor;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KeyMapping.class)
public abstract class KeyMappingMixin implements BackpackClientUtils.KeyMappingAccess {

    @Override
    public InputConstants.Key getKey() {
        return ((KeyMappingAccessor) this).resource_backpacks$getKey();
    }
}
