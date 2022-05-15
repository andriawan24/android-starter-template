plugins {
    id(Plugins.ANDROID_APP)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APP_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = VersionConfig.VERSION_CODE
        versionName = VersionConfig.VERSION_NAME

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
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
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common"))
    implementation(project(":common-ui"))

    // AndroidX
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE)
    implementation(AndroidX.LIFECYCLE_VIEW_MODEL)

    // Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.GSON_CONVERTER)

    // OKHTTP3 Logging
    implementation(OKHttp3.LOGGING_INTERCEPTOR)

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

kapt {
    correctErrorTypes = true
}