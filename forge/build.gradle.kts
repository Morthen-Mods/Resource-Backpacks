plugins {
    id("net.morthen.gradle.multiloader")
}

multiloader {
    loader = "forge"

    forgeVersion = providers.gradleProperty("forge")
    forgeMixins = listOf(
        "${ modId.get() }.mixins.json"
    )

    withModPublish()

    applyMetadataReplacements(listOf("pack.mcmeta", "META-INF/mods.toml"), mapOf(
        "forge_version" to forgeVersion.get(),
        "issues_url" to providers.gradleProperty("issues_url")
    ))
}

val resourcelibrary = providers.gradleProperty("resourcelibrary").get()
val resoucreconfigapi = providers.gradleProperty("resourceconfigapi").get()

dependencies {
    implementation("net.morthen:resourcelibrary-forge:$resourcelibrary")
    implementation("net.morthen:resourceconfigapi-forge:$resoucreconfigapi")
}