plugins {
    id("architectcoders.jvm.library")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}