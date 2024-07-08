plugins {
    id("architectcoders.jvm.library")
    id("architectcoders.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    testImplementation(project(":test:unit"))
}