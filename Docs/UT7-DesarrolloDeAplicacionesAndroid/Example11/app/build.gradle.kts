import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
}

android {
    namespace  = "com.raks.secrets"
    compileSdk = 33

    val apikeyProperties = Properties().apply {
        load(FileInputStream(rootProject.file("apikey.properties")))
    }

    defaultConfig {
        applicationId = "com.raks.secrets"
        minSdk        = 26
        targetSdk     = 33
        versionCode   = 1
        versionName   = "1.0.0"
    }

    buildTypes {
        debug {
            buildConfigField("String", "SECRET_ONE", apikeyProperties.getProperty("SECRET_ONE"))
            buildConfigField("String", "SECRET_TWO", apikeyProperties.getProperty("SECRET_TWO"))
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "SECRET_ONE", apikeyProperties.getProperty("SECRET_ONE"))
            buildConfigField("String", "SECRET_TWO", apikeyProperties.getProperty("SECRET_TWO"))
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}