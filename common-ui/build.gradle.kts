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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.COMPOSE_VERSION
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // AndroidX
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE)
    implementation(AndroidX.LIFECYCLE_VIEW_MODEL)

    // Compose
    implementation(Compose.COMPOSE_ACTIVITY)
    implementation(Compose.COMPOSE_UI)
    implementation(Compose.COMPOSE_MATERIAL)
    implementation(Compose.COMPOSE_TOOLING_PREVIEW)
    implementation(Compose.COMPOSE_LIFECYCLE)
    implementation(Compose.COMPOSE_NAVIGATION)
    debugImplementation(Compose.COMPOSE_TOOLING)
    debugImplementation(Compose.COMPOSE_TEST_MANIFEST)

    // Local Testing
    testImplementation(Test.JUNIT)
    testImplementation(Hilt.HILT_ANDROID_TESTING)
    kaptTest(Hilt.HILT_COMPILER)

    // Instrumentation Testing
    androidTestImplementation(Test.JUNIT_EXT)
    androidTestImplementation(Test.ESPRESSO)
    androidTestImplementation(Compose.COMPOSE_UI_TEST)
    androidTestImplementation(Hilt.HILT_ANDROID_TESTING)
    kaptAndroidTest(Hilt.HILT_COMPILER)
}