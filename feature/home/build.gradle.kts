plugins {
    id("architectcoders.android.feature")
    id("architectcoders.di.library.compose")
}

android {
    namespace = "io.devexpert.architectcoders.ui.home"
}

dependencies {
    implementation(project(":domain:movie"))
}