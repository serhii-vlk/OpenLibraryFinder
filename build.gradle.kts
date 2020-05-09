// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(GradlePlugins.android)
        classpath(kotlin("gradle-plugin", Versions.kotlin))
        classpath(GradlePlugins.navSafeArgs)
    }
}

plugins {
    id("com.github.ben-manes.versions").version(Versions.dependencyVersionPlugin)
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}
