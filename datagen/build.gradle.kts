plugins {
    id("net.morthen.gradle.multiloader")
}

multiloader {
    loader = "datagen"
    neoForgeVersion = providers.gradleProperty("neoforge")

    applyMetadataReplacements(listOf("pack.mcmeta", "META-INF/neoforge.mods.toml"))
}

val resourcelibrary = providers.gradleProperty("resourcelibrary").get()
val resoucreconfigapi = providers.gradleProperty("resourceconfigapi").get()

dependencies {
    implementation("net.morthen:resourcelibrary-neoforge:$resourcelibrary")
    implementation("net.morthen:resourceconfigapi-neoforge:$resoucreconfigapi")
}