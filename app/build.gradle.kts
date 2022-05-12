plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    id("kotlin-kapt")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = VersionConfig.versionCode
        versionName = VersionConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.2.0-alpha08"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycle)

    // Compose
    implementation(Compose.composeActivity)
    implementation(Compose.composeUI)
    implementation(Compose.composeMaterial)
    implementation(Compose.composeToolingPreview)
    debugImplementation(Compose.composeTooling)
    debugImplementation(Compose.composeTestManifest)

    // Timber Logging
    implementation(Log.timber)

    // Hilt
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    // Local Testing
    testImplementation(Test.jUnit)
    testImplementation(Hilt.hiltAndroidTesting)
    kaptTest(Hilt.hiltCompiler)

    // Instrumentation Testing
    androidTestImplementation(Test.jUnitExt)
    androidTestImplementation(Test.espresso)
    androidTestImplementation(Compose.composeUITest)
    androidTestImplementation(Hilt.hiltAndroidTesting)
    kaptAndroidTest(Hilt.hiltCompiler)
}

kapt {
    correctErrorTypes = true
}