/*
 * 31.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainUser(

        val id: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val imgSrcUrl: String

) : Parcelable