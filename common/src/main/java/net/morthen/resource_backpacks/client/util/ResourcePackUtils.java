package net.morthen.resource_backpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.repository.Pack;
import net.morthen.resource_backpacks.BackpackConstants;

import java.io.IOException;
import java.util.LinkedList;

public class ResourcePackUtils {
    static final Minecraft client =  Minecraft.getInstance();

    public static <T> LinkedList<T> readAllMetaData(MetadataSectionType<T> metadataSectionType) {
        LinkedList<T> allMetaData = new LinkedList<>();

        for (Pack pack : client.getResourcePackRepository().getSelectedPacks()) {
            try (PackResources resources = pack.open()) {
                T metadataSection = resources.getMetadataSection(metadataSectionType);
                if (metadataSection != null) {
                    allMetaData.add(metadataSection);
                }
            } catch (IOException e) {
                BackpackConstants.LOG.error("Error reading pack.mcmeta", e);
            }
        }

        return allMetaData;
    }
}
