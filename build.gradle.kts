plugins {
    id("java")
    id("idea")
    id("net.morthen.gradle.multiloader") version "0.1.13" apply false

    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT" apply false
    id("net.minecraftforge.gradle") version "[7.0.30, 8)" apply false
    id("net.neoforged.moddev") version "2.0.141" apply false

    id("me.modmuss50.mod-publish-plugin") version "2.2.0" apply false
}

subprojects {
    pluginManager.apply("idea")
    idea.module.isDownloadSources = true
}