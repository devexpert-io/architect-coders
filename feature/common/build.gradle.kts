plugins {
    id("architectcoders.android.library.compose")
}

android {
    namespace = "io.devexpert.architectcoders.ui.common"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}