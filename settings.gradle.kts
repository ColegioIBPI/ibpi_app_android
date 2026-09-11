pluginManagement {
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

rootProject.name = "MyIBPI"

include(":app")

include(":core:designsystem")
include(":core:navigation")
include(":core:ui")

include(":feature:announcements")
include(":feature:attendance")
include(":feature:finance")
include(":feature:info")
include(":feature:report")
