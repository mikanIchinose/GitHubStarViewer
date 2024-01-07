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

rootProject.name = "GitHub_Star_Viewer"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":feature:signin:ui")
include(":feature:signin:domain")
include(":feature:signin:data:api")
include(":feature:signin:data:impl")
include(":core:data:api")
include(":core:data:impl")
include(":core:datastore")
include(":feature:repositories:data:api")
include(":feature:repositories:data:impl")
include(":feature:repositories:ui")
include(":feature:repositories:domain")
include(":core:network")
include(":core:testing")
