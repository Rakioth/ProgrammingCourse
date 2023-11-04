package library

import Libs
import util.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RetrofitPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            dependencies {
                implementation(Libs.RETROFIT)
                implementation(Libs.RETROFIT_CONVERTER)
            }
        }
    }

}