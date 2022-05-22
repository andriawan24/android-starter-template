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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    // AndroidX
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE)

    // Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.GSON_CONVERTER)

    // OKHTTP3 Logging
    implementation(OKHttp3.LOGGING_INTERCEPTOR)

    // Timber Logging
    implementation(Log.TIMBER)

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
    testImplementation("io.mockk:mockk:1.12.4")

    // Instrumentation Testing
    androidTestImplementation(Test.JUNIT_EXT)
    androidTestImplementation(Test.ESPRESSO)
    androidTestImplementation(Compose.COMPOSE_UI_TEST)
    androidTestImplementation(Hilt.HILT_ANDROID_TESTING)
    androidTestImplementation(Room.ROOM_TESTING)
    kaptAndroidTest(Hilt.HILT_COMPILER)
}