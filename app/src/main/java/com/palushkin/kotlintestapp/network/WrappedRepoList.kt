/*
 * 21.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.network

import com.squareup.moshi.JsonQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WrappedRepoList {
}