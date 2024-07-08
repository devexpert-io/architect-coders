plugins {
    id("architectcoders.jvm.library")
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(project(":domain:region"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}