/*
 * 20.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserList (

//        val page: String,
//        @Json(name = "per_page")
//        val perPage: String,
//        val total: String,
//        @Json(name = "total_pages")
//        val totalPages: String,
        @Json(name = "data")
        val dataU: List<User>,
//        val support: Support



        //   "page":1,
        //   "per_page":6,
        //   "total":12,
        //   "total_pages":2,
        //   "data":[

    //         "id":1,
    //         "email":"george.bluth@reqres.in",
    //         "first_name":"George",
    //         "last_name":"Bluth",
    //         "avatar":"https://reqres.in/img/faces/1-image.jpg"
)