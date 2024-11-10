package net.xstopho.resource_backpacks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(FMLJavaModLoadingContext context) {
        BackpackConstants.commonInit();
    }
}
