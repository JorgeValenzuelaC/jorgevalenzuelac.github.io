plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.firetvstreams"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.firetvstreams"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Default playlist shipped with the app. Point this at any M3U/M3U8
        // playlist you are licensed to use (a free service, your own media
        // server, or a paid IPTV subscription). It can also be changed at
        // runtime from the in-app Settings screen.
        buildConfigField(
            "String",
            "DEFAULT_PLAYLIST_URL",
            "\"https://raw.githubusercontent.com/example/placeholder/main/sample.m3u\""
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")

    // Compose for TV
    implementation(platform("androidx.compose:compose-bom:2024.08.00"))
    implementation("androidx.tv:tv-foundation:1.0.0-alpha11")
    implementation("androidx.tv:tv-material:1.0.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")

    // DataStore for persisting the playlist URL
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Media player (ExoPlayer / Media3)
    implementation("androidx.media3:media3-exoplayer:1.4.1")
    implementation("androidx.media3:media3-ui:1.4.1")
    implementation("androidx.media3:media3-exoplayer-hls:1.4.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.4.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
