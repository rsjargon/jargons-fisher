rootProject.name = "jargons-fisher"

include("scripts:jargons-fisher")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}