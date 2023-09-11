plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android {
    namespace  = "com.iothar.data"
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        minSdk                    = Apps.MIN_SDK
        targetSdk                 = Apps.TARGET_SDK
        testInstrumentationRunner = Apps.TEST_INSTRUMENTATION_RUNNER
//        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = Apps.JAVA_COMPATIBILITY_VERSION
        targetCompatibility = Apps.JAVA_COMPATIBILITY_VERSION
    }

    kotlinOptions {
        jvmTarget = Apps.JVM_TARGET
    }
}

dependencies {
    implementation(Libs.ANDROIDX_CORE_KTX)
    implementation(Libs.ANDROIDX_APPCOMPAT)
    implementation(Libs.MATERIAL_ANDROID)
    implementation(Libs.ANDROIDX_ROOM_KTX)
    kapt(Libs.ANDROIDX_ROOM_COMPILER)
    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Libs.ANDROIDX_TEST_ESPRESSO)
}