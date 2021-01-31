/*
 * 31.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.palushkin.kotlintestapp.database.EntityDatabase
import com.palushkin.kotlintestapp.database.asDomainModel
import com.palushkin.kotlintestapp.domain.DomainUser
import com.palushkin.kotlintestapp.network.UserApi
import com.palushkin.kotlintestapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: EntityDatabase) {

    val domainUsers: LiveData<List<DomainUser>> =
        Transformations.map(database.userDao.getUsers()) {
            it.asDomainModel()
        }

    suspend fun refreshEntities() {
        withContext(Dispatchers.IO) {
            val userList = UserApi.retrofitService.getUserList()
            database.userDao.insertAll(*userList.asDatabaseModel())
        }
    }


}