plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.proyecto"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proyecto"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")

    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.appcompat:appcompat:1.3.0")

    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")

//firebase
    implementation("com.firebaseui:firebase-ui-firestore:8.0.2")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-storage:21.0.0")// base de archivos

    implementation("de.hdodenhof:circleimageview:3.1.0") // imagen circular
    implementation("com.github.bumptech.glide:glide:4.12.0") //recortar y descargar imagen


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}