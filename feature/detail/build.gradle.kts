plugins {
    id("architectcoders.android.feature")
}

android {
    namespace = "io.devexpert.architectcoders.ui.detail"
}

dependencies {
    implementation(project(":domain:movie"))
}