plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
}

android {

    namespace = "com.spotapp.mobile.data"

    defaultConfig {
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
}

dependencies {

    api(libs.kotlinx.coroutines.core)
    api(libs.androidx.room.runtime)
    api(libs.androidx.room.ktx)
    api(libs.firebase.auth)
    api(libs.firebase.firestore)
    api(libs.firebase.database)
    api(libs.androidx.datastore)

    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.room.compiler)
}

apply("$rootDir/config.gradle")
