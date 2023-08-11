plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.spotapp.mobile.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    testOptions {
        unitTests.all {
            it.jvmArgs("-noverify")
            it.exclude("android\\.R\\\$styleable", "com\\.android\\.internal\\.R\\\$styleable")
            kover {
                excludeSourceSets {
                    names("android\\.R\\\$styleable", "com\\.android\\.internal\\.R\\\$styleable")
                }
            }
        }
    }
}

dependencies {

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("androidx.room:room-runtime:2.5.2")
    api("androidx.room:room-ktx:2.5.2")

    annotationProcessor("androidx.room:room-compiler:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

}

apply("$rootDir/testing.gradle")