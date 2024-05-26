plugins {
    id("architectcoders.android.library")
    id("architectcoders.di.library")
}

android {
    namespace = "io.devexpert.architectcoders.framework.region"
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.play.services.location)
}