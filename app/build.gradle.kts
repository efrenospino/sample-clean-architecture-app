plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {

    namespace = "com.spotapp.mobile"
    compileSdk = 33

    defaultConfig {

        applicationId = "com.spotapp.mobile"
        minSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        configurations.all {
            resolutionStrategy {
                force("androidx.emoji2:emoji2-views-helper:1.3.0")
                force("androidx.emoji2:emoji2:1.3.0")
            }
        }
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    koverReport {
        filters {
            excludes {
                classes(
                    "*_Impl*",
                    "*Activity",
                    "*Application*",
                    "*.BuildConfig"
                )
                packages(
                    "android.*"
                )
            }
        }

        defaults {

            mergeWith("debug")

            filters { // common filters for all default Kover tasks
                excludes {
                    classes(
                        "*_Impl*",
                        "*Activity",
                        "*Application*",
                        "*.BuildConfig"
                    )
                    packages(
                        "android.*"
                    )
                }
            }

            xml {
                onCheck = true
            }

            html {
                onCheck = true
            }
        }
    }

}

dependencies {

    kover(project(":data"))
    kover(project(":domain"))
    kover(project(":ui"))

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":ui"))

    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

}

apply("$rootDir/testing.gradle")