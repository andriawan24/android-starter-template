import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id(Plugins.androidApp) version PluginVersions.androidApp apply false
    id(Plugins.library) version PluginVersions.library apply false
    id(Plugins.kotlin) version PluginVersions.kotlin apply false
    id(Plugins.detekt) version PluginVersions.detekt apply true
}

subprojects {
    apply(plugin = Plugins.detekt)

    detekt {
        toolVersion = PluginVersions.detekt
        config = files("${project.rootDir}/config/detekt/detekt.yml")
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
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
