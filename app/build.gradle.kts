import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("jacoco")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig {
        applicationId = AppConfig.id

        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)

        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        testInstrumentationRunnerArguments["runnerBuilder"] =
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        junitPlatform {
            jacocoOptions {
                html.enabled = true
                xml.enabled = false
                csv.enabled = false
            }
            filters {
                includeEngines("spek2")
            }
        }
        unitTests.all {
            it.testLogging.events("passed", "skipped", "failed")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.coroutinesCore)

    implementation(Deps.coreKtx)
    implementation(Deps.appcompat)
    implementation(Deps.fragment)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.recyclerView)
    implementation(Deps.navigationFragment)

    implementation(Deps.coil)

    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)

    implementation(Deps.rxJava)
    implementation(Deps.rxAndroid)

    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverterMoshi)
    implementation(Deps.moshi)
    kapt(Deps.moshiKotlinCodegen)

    implementation(Deps.timber)

    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
    testImplementation(Deps.spekDslJvm)
    testImplementation(Deps.spekRunnerJunit5)
    testImplementation(Deps.mockK)

    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.testRunner)
    androidTestImplementation(Deps.junit5AndroidTestCore)
    androidTestRuntimeOnly(Deps.junit5AndroidTestRunner)
    androidTestImplementation(Deps.mockKAndroid)
}
