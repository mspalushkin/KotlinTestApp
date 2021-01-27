/*
 * 20.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class User(

        val id: String,
        val email: String,
        @Json(name = "first_name")
        val firstName: String,
        @Json(name = "last_name")
        val lastName: String,
        @Json(name = "avatar")
        val imgSrcUrl: String

) : Parcelable