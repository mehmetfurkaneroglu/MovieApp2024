// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.7.4" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false

}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath ("com.android.tools.build:gradle:3.2.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51")
        classpath("com.google.gms:google-services:4.4.1")
    }
}