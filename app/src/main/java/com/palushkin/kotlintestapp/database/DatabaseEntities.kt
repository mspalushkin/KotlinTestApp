/*
 * 31.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.palushkin.kotlintestapp.domain.DomainUser

@Entity
data class DatabaseEntity constructor(

        @PrimaryKey
        val id: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val imgSrcUrl: String
)

fun List<DatabaseEntity>.asDomainModel(): List<DomainUser> {
    return map {
        DomainUser(
                id = it.id,
                email = it.email,
                firstName = it.firstName,
                lastName = it.lastName,
                imgSrcUrl = it.imgSrcUrl
        )
    }
}