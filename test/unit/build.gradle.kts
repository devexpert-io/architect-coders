plugins {
    id("architectcoders.jvm.library")
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}