package library

import Libs
import util.annotationProcessor
import util.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ViewsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            dependencies {
                implementation(Libs.ANDROIDX_ACTIVITY)
                implementation(Libs.ANDROIDX_APPCOMPAT)
                implementation(Libs.ANDROIDX_CONSTRAINTLAYOUT)
                implementation(Libs.ANDROIDX_CORE)
                implementation(Libs.ANDROIDX_VIEWMODEL)
                implementation(Libs.MATERIAL)
                implementation(Libs.GLIDE)
                annotationProcessor(Libs.GLIDE_COMPILER)
            }
        }
    }

}