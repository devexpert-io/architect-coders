package io.devexpert.architectcoders

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.addUnitTestDependencies() {
    dependencies {
        add("testImplementation", project.libs.findLibrary("junit").get())
        add("testImplementation", project.libs.findLibrary("mockito.kotlin").get())
    }
}

fun Project.addAndroidTestDependencies() {
    dependencies {
        add("androidTestImplementation", project(":test:unit"))
        add("androidTestImplementation", platform(libs.findLibrary("androidx.compose.bom").get()))
        add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
        add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
        add("androidTestImplementation", libs.findLibrary("androidx.test.core").get())
        add("androidTestImplementation", libs.findLibrary("androidx.test.rules").get())
        add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
    }
}