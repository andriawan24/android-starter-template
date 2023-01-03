import io.gitlab.arturbosch.detekt.Detekt
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id(Plugins.ANDROID_APP) version PluginVersions.ANDROID_APP apply false
    id(Plugins.LIBRARY) version PluginVersions.LIBRARY apply false
    id(Plugins.KOTLIN) version PluginVersions.KOTLIN apply false
    id(Plugins.DETEKT) version PluginVersions.DETEKT apply true
    id(Plugins.BEN_MANES_VERSION) version PluginVersions.BEN_MANES_VERSION apply false
    id(Plugins.HILT) version PluginVersions.HILT apply false
}

allprojects {
    apply(plugin = Plugins.BEN_MANES_VERSION)

    tasks.withType<DependencyUpdatesTask> {
        rejectVersionIf {
            val version = candidate.version
            val stableKeyword =
                listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
            val regex = "^[0-9,.v-]+(-r)?$".toRegex()
            val isStable = stableKeyword || regex.matches(version)
            isStable.not()
        }
    }
}

subprojects {
    apply(plugin = Plugins.DETEKT)

    detekt {
        toolVersion = PluginVersions.DETEKT
        config = files("${project.rootDir}/config/detekt/detekt.yml")
        autoCorrect = true
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            xml.required.set(true)
            xml.outputLocation.set(file("${project.rootDir}/build/reports/detekt.xml"))
            html.required.set(true)
            html.outputLocation.set(file("${project.rootDir}/build/reports/detekt.html"))
            txt.required.set(true)
            txt.outputLocation.set(file("${project.rootDir}/build/reports/detekt.txt"))
            sarif.required.set(false)
        }
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
