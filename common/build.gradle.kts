plugins {
    id("net.morthen.gradle.multiloader")
}

multiloader {
    neoFormVersion = providers.gradleProperty("neoform")
}

val resourcelibrary = providers.gradleProperty("resourcelibrary").get()
val resoucreconfigapi = providers.gradleProperty("resourceconfigapi").get()

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    compileOnly("org.ow2.asm:asm-tree:9.6")
    compileOnly("org.ow2.asm:asm:9.6")

    implementation("net.morthen:resourcelibrary-common:$resourcelibrary")
    implementation("net.morthen:resourceconfigapi-common:$resoucreconfigapi")
}