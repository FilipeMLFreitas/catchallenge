// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    //alias(libs.plugins.hiltAndroid) apply false
    //TODO: improve hilt dependency. defining it through gradle catalog is not working
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" apply false
    alias(libs.plugins.compose.compiler) apply false
}