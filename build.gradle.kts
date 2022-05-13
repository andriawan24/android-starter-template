import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id(Plugins.ANDROID_APP) version PluginVersions.ANDROID_APP apply false
    id(Plugins.LIBRARY) version PluginVersions.LIBRARY apply false
    id(Plugins.KOTLIN) version PluginVersions.KOTLIN apply false
    id(Plugins.DETEKT) version PluginVersions.DETEKT
}

subprojects {
    apply(plugin = Plugins.DETEKT)

    detekt {
        toolVersion = PluginVersions.DETEKT
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
