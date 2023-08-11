// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    id("org.jetbrains.kotlinx.kover") version "0.7.3" apply false
    id("io.gitlab.arturbosch.detekt") version ("1.23.1") apply false
}

allprojects {
    apply {
        plugin("org.jetbrains.kotlinx.kover")
        plugin("io.gitlab.arturbosch.detekt")
    }
}

val projectSource = file(projectDir)
val kotlinFiles = "**/*.kt"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"

tasks.register("detektAll", io.gitlab.arturbosch.detekt.Detekt::class) {
    description = "Custom DETEKT build for all modules"
    parallel = true
    ignoreFailures = false
    autoCorrect = false
    buildUponDefaultConfig = true
    setSource(projectSource)
    include(kotlinFiles)
    exclude(resourceFiles, buildFiles)
    reports {
        sarif.required.set(true)
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
    }
}

