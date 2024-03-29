object AndroidX {
    private const val CORE_KTX_VERSION = "1.9.0"
    private const val LIFECYCLE_VERSION = "2.5.1"
    const val CORE_KTX = "androidx.core:core-ktx:$CORE_KTX_VERSION"
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
    const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
}

object Retrofit {
    private const val VERSION = "2.9.0"
    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:$VERSION"
    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$VERSION"
}

object OKHttp3 {
    private const val VERSION = "4.10.0"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$VERSION"
}

object Compose {
    private const val COMPOSE_ACTIVITY_VERSION = "1.6.1"
    private const val NAV_VERSION = "2.5.3"
    private const val HILT_NAVIGATION_VERSION = "1.0.0"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:2022.12.00"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:$COMPOSE_ACTIVITY_VERSION"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material"
    const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling"
    const val COMPOSE_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4"
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:$NAV_VERSION"
    const val COMPOSE_LIFECYCLE = "androidx.compose.runtime:runtime-livedata"
    const val COMPOSE_HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:$HILT_NAVIGATION_VERSION"
}

object Room {
    private const val ROOM_VERSION = "2.4.3"
    const val RUNTIME = "androidx.room:room-runtime:$ROOM_VERSION"
    const val COMPILER = "androidx.room:room-compiler:$ROOM_VERSION"
    const val ROOM_KTX = "androidx.room:room-ktx:$ROOM_VERSION"
    const val ROOM_TESTING = "androidx.room:room-testing:$ROOM_VERSION"
}

object Log {
    private const val VERSION = "5.0.1"
    const val TIMBER = "com.jakewharton.timber:timber:$VERSION"
}

object Coil {
    private const val COIL_VERSION = "2.2.2"
    const val COIL_KT = "io.coil-kt:coil-compose:$COIL_VERSION"
}

object Hilt {
    private const val VERSION = "2.44.2"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:$VERSION"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:$VERSION"
    const val HILT_ANDROID_TESTING = "com.google.dagger:hilt-android-testing:$VERSION"
}

object Test {
    private const val JUNIT_VERSION = "4.13.2"
    private const val JUNIT_EXT_VERSION = "1.1.4"
    private const val ESPRESSO_VERSION = "3.5.0"
    private const val IO_MOCK_VERSION = "1.13.3"

    const val JUNIT = "junit:junit:$JUNIT_VERSION"
    const val JUNIT_EXT = "androidx.test.ext:junit:$JUNIT_EXT_VERSION"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
    const val IO_MOCK = "io.mockk:mockk:$IO_MOCK_VERSION"
}

object Detekt {
    private const val DETEKT_VERSION = "1.22.0"
    const val DETEKT_AUTO_CORRECT = "io.gitlab.arturbosch.detekt:detekt-formatting:$DETEKT_VERSION"
}