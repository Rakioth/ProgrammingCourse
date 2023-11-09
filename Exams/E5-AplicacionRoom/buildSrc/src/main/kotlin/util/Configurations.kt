package util

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun LibraryExtension.configBuildTypes() {
    buildTypes {
        release {
            isMinifyEnabled = Apps.IS_MINIFY_ENABLED
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
}

internal fun CommonExtension<*, *, *, *>.configAndroid() {
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        minSdk = Apps.MIN_SDK
    }

    compileOptions {
        sourceCompatibility = Apps.JAVA_COMPATIBILITY_VERSION
        targetCompatibility = Apps.JAVA_COMPATIBILITY_VERSION
    }
}

internal fun BaseAppModuleExtension.configApplication() {
    namespace = Apps.APPLICATION_ID

    defaultConfig {
        applicationId = Apps.APPLICATION_ID
        targetSdk     = Apps.TARGET_SDK
        versionCode   = Apps.VERSION_CODE
        versionName   = Apps.VERSION_NAME
    }

    buildTypes {
        release {
            isMinifyEnabled   = Apps.IS_MINIFY_ENABLED
            isShrinkResources = Apps.IS_SHRINK_RESOURCES
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    buildFeatures {
        viewBinding = true
    }
}