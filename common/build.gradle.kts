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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(Compose.COMPOSE_MATERIAL)
    testImplementation(Test.JUNIT)
    androidTestImplementation(Test.JUNIT_EXT)
    androidTestImplementation(Test.ESPRESSO)

    // Compose
    implementation(Compose.COMPOSE_ACTIVITY)
    implementation(Compose.COMPOSE_UI)
    implementation(Compose.COMPOSE_MATERIAL)
    implementation(Compose.COMPOSE_TOOLING_PREVIEW)
    implementation(Compose.COMPOSE_LIFECYCLE)
    implementation(Compose.COMPOSE_NAVIGATION)
    implementation(Compose.COMPOSE_HILT)
    debugImplementation(Compose.COMPOSE_TOOLING)
    debugImplementation(Compose.COMPOSE_TEST_MANIFEST)

    // Detekt autoCorrect
    detektPlugins(Detekt.DETEKT_AUTO_CORRECT)

    // Timber logging
    implementation(Log.TIMBER)
}