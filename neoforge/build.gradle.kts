plugins {
    id("net.morthen.gradle.multiloader")
}

multiloader {
    loader = "neoforge"
    neoForgeVersion = providers.gradleProperty("neoforge")

    withModPublish {
        required.set(listOf(
            "resource-library",
            "resource-config-api"
        ))
    }

    applyMetadataReplacements(listOf("pack.mcmeta", "META-INF/neoforge.mods.toml"), mapOf(
        "neoforge_version" to neoForgeVersion.get(),
        "sources_url" to providers.gradleProperty("sources_url").get(),
        "issues_url" to providers.gradleProperty("issues_url").get()
    ))
}

val resourcelibrary = providers.gradleProperty("resourcelibrary").get()
val resoucreconfigapi = providers.gradleProperty("resourceconfigapi").get()

dependencies {
    implementation("net.morthen:resourcelibrary-neoforge:$resourcelibrary")
    implementation("net.morthen:resourceconfigapi-neoforge:$resoucreconfigapi")
}