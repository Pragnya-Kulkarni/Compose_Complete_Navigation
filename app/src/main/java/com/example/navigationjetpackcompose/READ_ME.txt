
https://developer.android.com/jetpack/compose/navigation

Navigating with Compose
==========================
The Navigation component provides support for Jetpack Compose applications.
You can navigate between composables while taking advantage of the Navigation
component’s infrastructure and features.


Setup
===========
To support Compose, use the following dependency in your app module’s build.gradle file:

dependencies {
    def nav_version = "2.5.0"

    implementation "androidx.navigation:navigation-compose:$nav_version"
}

