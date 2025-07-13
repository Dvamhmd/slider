import org.gradle.kotlin.dsl.libs

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.dots"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dots"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_BASE_URL", "\"https://anala.my.id/tehidaman/api/\"")
        }
        release {
            buildConfigField("String", "API_BASE_URL", "\"https://anala.my.id/tehidaman/api/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures{
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        buildConfig = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {



    //glide
    implementation(libs.glide)
    ksp(libs.compiler)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //dots indicator
    implementation(libs.dotsindicator)

    //scalable ssp sdp
    implementation(libs.ssp)
    implementation(libs.sdp)

    //bottom navigation
    implementation(libs.curved.bottom.navigation)

    //ios switch
    implementation(libs.switch.button)

    //osmdroid
    implementation(libs.osmdroid)

    //preferences
    implementation(libs.androidx.preference)

    //google play service
    implementation(libs.play.services.location)
    implementation(libs.playservices.maps)


    //google maps
    implementation(libs.google.maps)
    implementation(libs.google.location)
    implementation(libs.places)

    
    //alert
    implementation(libs.material.v1110)


    // Retrofit untuk Networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Coroutines untuk menjalankan network call
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Midtrans Mobile SDK v2
    implementation(libs.uikit)


    //lottie loading animation
    implementation(libs.lottie)

    implementation(libs.shimmer)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



}