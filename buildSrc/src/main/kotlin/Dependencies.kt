object AndroidX {
    private const val coreKTXVersion = "1.7.0"
    private const val lifecycleVersion = "2.4.1"

    const val coreKtx = "androidx.core:core-ktx:$coreKTXVersion"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
}

object Compose {
    private const val composeVersion = "1.1.1"
    private const val composeActivityVersion = "1.4.0"

    const val composeUI = "androidx.compose.ui:ui:$composeVersion"
    const val composeActivity = "androidx.activity:activity-compose:$composeActivityVersion"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    const val composeUITest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
}

object Log {
    private const val timberVersion = "5.0.1"

    const val timber = "com.jakewharton.timber:timber:$timberVersion"
}

object Test {
    private const val jUnitVersion = "4.13.2"
    private const val jUnitExtVersion = "1.1.3"
    private const val espressoVersion = "3.4.0"

    const val jUnit = "junit:junit:$jUnitVersion"
    const val jUnitExt = "androidx.test.ext:junit:$jUnitExtVersion:"
    const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
}
