import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

val tokenApi: String = gradleLocalProperties(rootDir).getProperty("TOKEN_API")

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
            buildConfigField("String", "TOKEN_API", "\"$tokenApi\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
            buildConfigField("String", "TOKEN_API", "\"$tokenApi\"")

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    getCommonDependencies()
    getNetworkDependencies()
    getHiltDependencies()
    getDatabaseDependencies()
    getCommonTestingDependencies()

    // Detekt autoCorrect
    detektPlugins(Detekt.DETEKT_AUTO_CORRECT)
}