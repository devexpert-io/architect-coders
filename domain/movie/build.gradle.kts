plugins {
    id("architectcoders.jvm.library")
    id("architectcoders.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}