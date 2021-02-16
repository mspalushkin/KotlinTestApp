/*
 * 12.02.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.2"

    const val junit = "junit:junit:4.13"

    const val material = "com.google.android.material:material:1.1.0"

    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:1.1.0"

    object Kotlin {
        private const val version = "1.4.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Coroutines {
        private const val version = "1.4.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"

        const val coreKtx = "androidx.core:core-ktx:1.3.2"

        object Room {
            private const val version = "2.2.6"
            const val runtime = "androidx.room:room-runtime:${version}"
            const val ktx = "androidx.room:room-ktx:${version}"
            const val compiler = "androidx.room:room-compiler:${version}"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }
    }

}