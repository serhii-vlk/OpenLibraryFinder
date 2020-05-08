// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(GradlePlugins.android)
        classpath(kotlin("gradle-plugin"))
        classpath(GradlePlugins.androidJUnit5)
        classpath(GradlePlugins.jacocoCore)
    }
}

plugins {
    id("com.github.ben-manes.versions").version(Versions.dependencyVersionPlugin)
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://dl.bintray.com/spekframework/spek-dev")
        }
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}
