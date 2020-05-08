plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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

    sourceSets.forEach {
        it.java.srcDirs("src/${it.name}/kotlin")
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
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }

    testOptions {
        unitTests.all {
            it.testLogging.events("passed", "skipped", "failed")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.coroutinesCore)

    implementation(Deps.coreKtx)
    implementation(Deps.appcompat)
    implementation(Deps.fragment)
    implementation(Deps.activity)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.recyclerView)
    implementation(Deps.swipeRepreshLayout)
    implementation(Deps.navigationFragment)

    implementation(Deps.coil)

    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)

    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverterMoshi)
    implementation(Deps.okhttpLoggingInteractor)
    implementation(Deps.moshi)
    kapt(Deps.moshiKotlinCodegen)

    implementation(Deps.timber)

    testImplementation(kotlin("reflect"))
    testImplementation(Deps.truth)
    testImplementation(Deps.coroutinesTest)
    testImplementation(Deps.junit5JupiterApi)
    testRuntimeOnly(Deps.junit5JupiterEngine)
    testImplementation(Deps.mockK)

    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.testRunner)
    androidTestImplementation(Deps.mockKAndroid)
}
