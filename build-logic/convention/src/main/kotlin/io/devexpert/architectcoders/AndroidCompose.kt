package io.devexpert.architectcoders

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    commonExtension.apply {

        buildFeatures {
            compose = true
        }

    }

    dependencies {
        val composeBom = libs.findLibrary("androidx.compose.bom").get()
        add("implementation", platform(composeBom))
        add("implementation", libs.findLibrary("androidx.ui").get())
        add("implementation", libs.findLibrary("androidx.ui.graphics").get())
        add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
        add("implementation", libs.findLibrary("androidx.material3").get())
        add("implementation", libs.findLibrary("coil.compose").get())
        add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
    }

}