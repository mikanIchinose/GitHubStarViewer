pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GitHubStarViewer"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:data:api")
include(":core:data:impl")
include(":core:datastore")
include(":core:network")
include(":core:testing")
include(":feature:repositories:data")
include(":feature:repositories:ui")
include(":feature:repositories:domain")
include(":feature:signin:ui")
include(":feature:signin:domain")
include(":feature:signin:data:api")
include(":feature:signin:data:impl")
