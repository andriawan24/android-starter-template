import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.getCommonDependencies() {
    "implementation"(AndroidX.CORE_KTX)
    "implementation"(AndroidX.LIFECYCLE)
    "implementation"(AndroidX.LIFECYCLE_VIEW_MODEL)
    "implementation"(Log.TIMBER)
}

fun DependencyHandlerScope.getComposeDependencies() {
    "implementation"(platform(Compose.COMPOSE_BOM))
    "implementation"(Compose.COMPOSE_ACTIVITY)
    "implementation"(Compose.COMPOSE_UI)
    "implementation"(Compose.COMPOSE_MATERIAL)
    "implementation"(Compose.COMPOSE_TOOLING_PREVIEW)
    "implementation"(Compose.COMPOSE_LIFECYCLE)
    "implementation"(Compose.COMPOSE_NAVIGATION)
    "implementation"(Compose.COMPOSE_HILT_NAVIGATION)
    "debugImplementation"(platform(Compose.COMPOSE_BOM))
    "debugImplementation"(Compose.COMPOSE_TOOLING)
    "debugImplementation"(Compose.COMPOSE_TEST_MANIFEST)
    "implementation"(Coil.COIL_KT)

    // Testing
    "androidTestImplementation"(platform(Compose.COMPOSE_BOM))
    "androidTestImplementation"(Hilt.HILT_ANDROID_TESTING)
}

fun DependencyHandlerScope.getNetworkDependencies() {
    "implementation"(Retrofit.RETROFIT2)
    "implementation"(Retrofit.GSON_CONVERTER)
    "implementation"(OKHttp3.LOGGING_INTERCEPTOR)
}

fun DependencyHandlerScope.getHiltDependencies() {
    "implementation"(Hilt.HILT_ANDROID)
    "kapt"(Hilt.HILT_COMPILER)

    // Testing
    "kaptAndroidTest"(Hilt.HILT_COMPILER)
    "androidTestImplementation"(Hilt.HILT_ANDROID_TESTING)
    "testImplementation"(Hilt.HILT_ANDROID_TESTING)
    "kaptTest"(Hilt.HILT_COMPILER)
}

fun DependencyHandlerScope.getDatabaseDependencies() {
    "implementation"(Room.RUNTIME)
    "kapt"(Room.COMPILER)
    "implementation"(Room.ROOM_KTX)

    // Testing
    "testImplementation"(Room.ROOM_TESTING)
    "androidTestImplementation"(Room.ROOM_TESTING)
}

fun DependencyHandlerScope.getCommonTestingDependencies() {
    // Local Testing
    "testImplementation"(Test.JUNIT)
    "testImplementation"(Test.IO_MOCK)

    // Instrumentation Testing
    "androidTestImplementation"(Test.JUNIT_EXT)
    "androidTestImplementation"(Test.ESPRESSO)
}