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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":common"))

    getCommonDependencies()

    // Retrofit
    implementation(Retrofit.RETROFIT2)
    implementation(Retrofit.GSON_CONVERTER)

    // Hilt
    implementation(Hilt.HILT_ANDROID)
    kapt(Hilt.HILT_COMPILER)

    // Room Database
    implementation(Room.RUNTIME)
    kapt(Room.COMPILER)
    implementation(Room.ROOM_KTX)

    // Local Testing
    testImplementation(Test.JUNIT)
    testImplementation(Hilt.HILT_ANDROID_TESTING)
    testImplementation(Room.ROOM_TESTING)
    kaptTest(Hilt.HILT_COMPILER)
    testImplementation("io.mockk:mockk:1.13.3")

    // Instrumentation Testing
    androidTestImplementation(platform(Compose.COMPOSE_BOM))
    androidTestImplementation(Test.JUNIT_EXT)
    androidTestImplementation(Test.ESPRESSO)
    androidTestImplementation(Compose.COMPOSE_UI_TEST)
    androidTestImplementation(Hilt.HILT_ANDROID_TESTING)
    androidTestImplementation(Room.ROOM_TESTING)
    kaptAndroidTest(Hilt.HILT_COMPILER)

    // Detekt autoCorrect
    detektPlugins(Detekt.DETEKT_AUTO_CORRECT)
}