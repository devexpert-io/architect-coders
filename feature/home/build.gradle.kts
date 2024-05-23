plugins {
    id("architectcoders.android.feature")
}

android {
    namespace = "io.devexpert.architectcoders.ui.home"
}

dependencies {
    implementation(project(":domain:movie"))
}