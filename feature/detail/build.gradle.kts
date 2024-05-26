plugins {
    id("architectcoders.android.feature")
    id("architectcoders.di.library.compose")
}

android {
    namespace = "io.devexpert.architectcoders.ui.detail"
}

dependencies {
    implementation(project(":domain:movie"))
}