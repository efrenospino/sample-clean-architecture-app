pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {

            version("activityCompose", "1.7.2")
            version("androidGradlePlugin", "8.1.0")
            version("appcompat", "1.6.1")
            version("coreKtx", "1.10.1")
            version("coreSplashscreen", "1.0.1")
            version("firebaseAnalytics", "21.3.0")
            version("firebaseCrashlytics", "18.4.0")
            version("firebaseAuth", "22.3.0")
            version("firebaseFirestore", "24.10.0")
            version("dataStore", "1.0.0")
            version("junitKtx", "1.1.5")
            version("junit", "4.13.2")
            version("kotlinxCoroutinesCore", "1.7.3")
            version("ktlintComposeRules", "0.0.26")
            version("lifecycleRuntimeKtx", "2.6.1")
            //version("material", "1.9.0")
            version("material3", "1.1.1")
            version("mockkAndroid", "1.13.5")
            version("navigationCompose", "2.7.1")
            version("robolectric", "4.10.3")
            version("roomKtx", "2.5.2")
            version("roomRuntime", "2.5.2")
            version("ui", "1.5.0")
            version("androidKotlin", "1.9.10")
            version("googleKsp", "1.9.0-1.0.13")
            version("kotlinxKover", "0.7.3")
            version("detekt", "1.23.1")
            version("googleServices", "4.3.15")
            version("firebaseCrashlyticsPlugin", "2.9.9")
            version("firebaseAppdistribution", "4.0.0")
            version("ktlint", "11.5.1")

            library(
                "androidx-activity-compose",
                "androidx.activity",
                "activity-compose"
            ).versionRef("activityCompose")
            library("androidx-appcompat", "androidx.appcompat", "appcompat").versionRef("appcompat")
            library("androidx-core-ktx", "androidx.core", "core-ktx").versionRef("coreKtx")
            library(
                "androidx-core-splashscreen",
                "androidx.core",
                "core-splashscreen"
            ).versionRef("coreSplashscreen")
            library("androidx-junit-ktx", "androidx.test.ext", "junit-ktx").versionRef("junitKtx")
            library(
                "androidx-lifecycle-runtime-ktx",
                "androidx.lifecycle",
                "lifecycle-runtime-ktx"
            ).versionRef("lifecycleRuntimeKtx")
            library(
                "androidx-lifecycle-viewmodel-compose",
                "androidx.lifecycle",
                "lifecycle-viewmodel-compose"
            ).versionRef("lifecycleRuntimeKtx")
            library(
                "androidx-material3",
                "androidx.compose.material3",
                "material3"
            ).versionRef("material3")
            library(
                "androidx-room-compiler",
                "androidx.room",
                "room-compiler"
            ).versionRef("roomKtx")
            library("androidx-room-ktx", "androidx.room", "room-ktx").versionRef("roomKtx")
            library(
                "androidx-room-room-compiler",
                "androidx.room",
                "room-compiler"
            ).versionRef("roomKtx")
            library(
                "androidx-room-runtime",
                "androidx.room",
                "room-runtime"
            ).versionRef("roomRuntime")
            library("androidx-ui", "androidx.compose.ui", "ui").versionRef("ui")
            library("androidx-ui-graphics", "androidx.compose.ui", "ui-graphics").versionRef("ui")
            library(
                "androidx-ui-test-manifest",
                "androidx.compose.ui",
                "ui-test-manifest"
            ).versionRef("ui")
            library("androidx-ui-tooling", "androidx.compose.ui", "ui-tooling").versionRef("ui")
            library(
                "androidx-ui-tooling-preview",
                "androidx.compose.ui",
                "ui-tooling-preview"
            ).versionRef("ui")
            library(
                "firebase-crashlytics",
                "com.google.firebase",
                "firebase-crashlytics"
            ).versionRef("firebaseCrashlytics")
            library(
                "firebase-auth",
                "com.google.firebase",
                "firebase-auth"
            ).versionRef("firebaseAuth")
            library(
                "firebase-firestore",
                "com.google.firebase",
                "firebase-firestore"
            ).versionRef("firebaseFirestore")
            library(
                "androidx-datastore",
                "androidx.datastore",
                "datastore-preferences"
            ).versionRef("dataStore")
            library(
                "androidx-navigation-compose",
                "androidx.navigation",
                "navigation-compose"
            ).versionRef("navigationCompose")
            library(
                "firebase-analytics",
                "com.google.firebase",
                "firebase-analytics"
            ).versionRef("firebaseAnalytics")
            library("junit", "junit", "junit").versionRef("junit")
            library(
                "kotlinx-coroutines-core",
                "org.jetbrains.kotlinx",
                "kotlinx-coroutines-core"
            ).versionRef("kotlinxCoroutinesCore")
            library(
                "kotlinx-coroutines-test",
                "org.jetbrains.kotlinx",
                "kotlinx-coroutines-test"
            ).versionRef("kotlinxCoroutinesCore")
            library(
                "ktlint-compose-rules",
                "com.twitter.compose.rules",
                "ktlint"
            ).versionRef("ktlintComposeRules")
            //library("material", "com.google.android.material", "material").versionRef("material")
            library("robolectric", "org.robolectric", "robolectric").versionRef("robolectric")
            library("mockk-android", "io.mockk", "mockk-android").versionRef("mockkAndroid")

            plugin(
                "android-application",
                "com.android.application"
            ).versionRef("androidGradlePlugin")
            plugin("android-library", "com.android.library").versionRef("androidGradlePlugin")
            plugin("android-kotlin", "org.jetbrains.kotlin.android").versionRef("androidKotlin")
            plugin("google-ksp", "com.google.devtools.ksp").versionRef("googleKsp")
            plugin("kotlinx-kover", "org.jetbrains.kotlinx.kover").versionRef("kotlinxKover")
            plugin("detekt", "io.gitlab.arturbosch.detekt").versionRef("detekt")
            plugin("google-services", "com.google.gms.google-services").versionRef("googleServices")
            plugin(
                "google-firebase-crashlytics",
                "com.google.firebase.crashlytics"
            ).versionRef("firebaseCrashlyticsPlugin")
            plugin(
                "google-firebase-appdistribution",
                "com.google.firebase.appdistribution"
            ).versionRef("firebaseAppdistribution")
            plugin("ktlint", "org.jlleitschuh.gradle.ktlint").versionRef("ktlint")
        }
    }
}

rootProject.name = "Spot"

include(":app")
include(":ui")
include(":data")
include(":domain")
