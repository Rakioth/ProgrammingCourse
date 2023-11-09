package module

import Apps
import Plugins
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import util.configAndroid
import util.configBuildTypes
import util.implementation

class DataPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(Plugins.ANDROID_LIBRARY)
                apply(Plugins.KOTLIN_ANDROID)
                apply(Plugins.ROOM)
                apply(Plugins.HILT)
            }

            extensions.getByType<LibraryExtension>().apply {
                namespace = "${Apps.APPLICATION_ID}.data"
                this.configAndroid()
                this.configBuildTypes()
            }

            dependencies {
                implementation(project(":domain"))
            }
        }
    }

}