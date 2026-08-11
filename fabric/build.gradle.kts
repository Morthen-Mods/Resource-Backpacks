plugins {
    id("net.morthen.gradle.multiloader")
}

multiloader {
    loader = "fabric"

    fabricApiVersion = providers.gradleProperty("fabric_api")
    fabricLoaderVersion = providers.gradleProperty("fabric_loader")

    withModPublish {
        required.set(listOf(
            "fabric-api"
        ))
        optional.set(listOf(
            "modmenu"
        ))
    }

    applyMetadataReplacements(listOf("pack.mcmeta", "*.mixins.json", "fabric.mod.json"), mapOf(
        "fabric_api" to fabricApiVersion.get(),
        "fabric_loader" to fabricLoaderVersion.get(),
        "sources_url" to providers.gradleProperty("sources_url").get(),
        "issues_url" to providers.gradleProperty("issues_url").get()
    ))
}

val resourcelibrary = providers.gradleProperty("resourcelibrary").get()
val resoucreconfigapi = providers.gradleProperty("resourceconfigapi").get()

dependencies {
    implementation("com.terraformersmc:modmenu:20.0.1")
    implementation("net.morthen:resourcelibrary-fabric:$resourcelibrary")
    implementation("net.morthen:resourceconfigapi-fabric:$resoucreconfigapi")
}