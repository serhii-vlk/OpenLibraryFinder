object Versions {
    const val androidGradlePlugin = "4.1.0-alpha09"
    const val androidJunit5Plugin = "1.6.2.0"
    const val jacocoPlugin = "0.8.5"
    const val dependencyVersionPlugin = "0.28.0"

    const val kotlin = "1.3.72"
    const val coroutines = "1.3.6"
    const val coreKtx = "1.3.0-rc01"
    const val appcompat = "1.2.0-beta01"
    const val activity = "1.1.0"
    const val archCore = "2.1.0"
    const val fragment = "1.3.0-alpha04"
    const val material = "1.2.0-alpha06"
    const val constraintLayout = "2.0.0-beta4"
    const val recyclerView = "1.2.0-alpha03"
    const val swipeRefreshLayout = "1.0.0"
    const val navigation = "2.3.0-alpha06"
    const val lifecycle = "2.3.0-alpha02"
    const val paging = "2.1.1"
    const val dagger = "2.27"
    const val moshi = "1.9.2"
    const val retrofit = "2.8.1"
    const val okhttp = "4.5.0"
    const val rxJava = "3.0.3"
    const val rxAndroid = "3.0.0"
    const val rxKotlin = "3.0.0"
    const val coil = "0.10.1"
    const val insetter = "0.2.2"

    const val timber = "4.7.1"

    const val junit5 = "5.7.0-M1"
    const val junit5Companion = "1.2.0"
    const val testCore = "1.3.0-beta01"
    const val testExtJunit = "1.1.2-beta01"
    const val testExtTruth = "1.3.0-beta01"
    const val truth = "1.0.1"
    const val mockK = "1.10.0"
    const val espresso = "3.3.0-beta01"
    const val spek = "2.1.0+"
}

object GradlePlugins {
    const val android = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val androidJUnit5 =
        "de.mannodermaus.gradle.plugins:android-junit5:${Versions.androidJunit5Plugin}"
    const val jacocoCore = "org.jacoco:org.jacoco.core:${Versions.jacocoPlugin}"
}

object Deps {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val swipeRepreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    const val insetter = "dev.chrisbanes:insetter-ktx:${Versions.insetter}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
    const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"

    const val okhttpLoggingInteractor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val coil = "io.coil-kt:coil:${Versions.coil}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // testing
    const val junit5JupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val junit5JupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val junit5AndroidTestCore =
        "de.mannodermaus.junit5:android-test-core:${Versions.junit5Companion}"
    const val junit5AndroidTestRunner =
        "de.mannodermaus.junit5:android-test-runner:${Versions.junit5Companion}"
    const val testCore = "androidx.test:core:${Versions.testCore}"
    const val testRunner = "androidx.test:runner:${Versions.testCore}"
    const val testRules = "androidx.test:rules:${Versions.testCore}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtJunit}"
    const val testExtTruth = "androidx.test.ext:truth:${Versions.testExtTruth}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCore}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val mockKAndroid = "io.mockk:mockk-android:${Versions.mockK}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val spekDslJvm = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
    const val spekRunnerJunit5 = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
}
