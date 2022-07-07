import org.gradle.api.JavaVersion

object Config {
    const val application_id = "by.romanovich.mydictinary"
    const val compile_sdk = 32
    const val min_sdk = 26
    const val target_sdk = 32
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val historyScreen = ":historyScreen"
}

object Versions {
    //Design
    const val appcompat = "1.0.2"
    const val material = "1.6.0"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.4.3"
    const val coroutinesAndroid = "1.4.3"

    //Retrofit
    const val retrofit = "2.6.0"
    const val converterGson = "2.6.0"
    const val interceptor = "3.12.1"
    const val adapterCoroutines = "0.9.2"

    //Koin
//    const val koinAndroid = "2.1.6"
//    const val koinViewModel = "2.1.6"
    const val koinAndroid = "3.1.2"
    const val koinViewModel = "3.1.2"
    const val koinAndroidCompat = "3.1.2"
    const val koinCore = "3.1.2"

    //Coil
    const val coil = "0.11.0"

    //Room
    const val roomKtx = "2.4.2"
    const val runtime = "2.4.2"
    const val roomCompiler = "2.4.2"

    //Test
    const val jUnit = "4.13.2"
    const val runner = "1.4.0"
    const val espressoCore = "3.4.0"

    //Rx-Java

    const val rxandroid = "2.1.0"
    const val rxjava = "2.2.8"

    // Picasso

    const val picasso = "2.71828"

    // Glide

    const val glide = "4.11.0"
    const val glideCompiler = "4.11.0"
    //    implementation 'com.github.bumptech.glide:glide:4.11.0'
//    kapt 'com.github.bumptech.glide:compiler:4.11.0'
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_android =
        "io.insert-koin:koin-android:${Versions.koinAndroid}" // проверь io или org

    //    const val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koin_android_compat =
        "io.insert-koin:koin-android-compat:${Versions.koinAndroidCompat}"
}

//    implementation "io.insert-koin:koin-core:$koin_version"
//    implementation "io.insert-koin:koin-android:$koin_version"
//    implementation "io.insert-koin:koin-android-compat:$koin_version"

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object RxJava {
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:${Versions.glideCompiler}"
}

