plugins {
    id("architectcoders.android.library")
    id("architectcoders.android.room")
    id("architectcoders.jvm.retrofit")
}

android {
    namespace = "io.devexpert.architectcoders.framework.core"
}

dependencies {
    implementation(project(":framework:movie"))
}