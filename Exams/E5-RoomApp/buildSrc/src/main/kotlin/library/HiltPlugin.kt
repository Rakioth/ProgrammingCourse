package library

import Libs
import Plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.implementation
import util.ksp

class HiltPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply(Plugins.HILT_GRADLE)
                apply(Plugins.KSP)
            }

            dependencies {
                implementation(Libs.HILT)
                ksp(Libs.HILT_COMPILER)
            }
        }
    }

}