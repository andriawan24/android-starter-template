plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    getCommonDependencies()
    getComposeDependencies()

    implementation(Compose.COMPOSE_MATERIAL)

    testImplementation(Test.JUNIT)
    androidTestImplementation(Test.JUNIT_EXT)
    androidTestImplementation(Test.ESPRESSO)

    // Detekt autoCorrect
    detektPlugins(Detekt.DETEKT_AUTO_CORRECT)

    // Timber logging
    implementation(Log.TIMBER)
}