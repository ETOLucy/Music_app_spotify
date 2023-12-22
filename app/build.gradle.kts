plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.music_app_spotify"
    compileSdk = 34
    compileSdkPreview="UpsideDownCake"
    defaultConfig {
        applicationId = "com.example.music_app_spotify"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "34.0.0"
}


dependencies {
//    implementation ("com.spotify.android.appremote:app-remote-lib:0.7.0")
//    implementation ("com.spotify.android.appremote:spotify-player-24h:0.7.0")

    implementation("com.spotify.android:auth:1.2.6")
    implementation ("androidx.browser:browser:1.6.0")
    implementation("androidx.appcompat:appcompat")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(files("C:\\Users\\yly\\Downloads\\spotify-app-remote-release-0.8.0.aar"))
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}