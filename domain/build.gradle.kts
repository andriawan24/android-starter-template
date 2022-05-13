plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
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
    implementation(project(":common"))

    // AndroidX
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE)

    // Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.GSON_CONVERTER)

    // Timber Logging
    implementation(Log.TIMBER)

    // Hilt
    implementation(Hilt.HILT_ANDROID)
    kapt(Hilt.HILT_COMPILER)

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