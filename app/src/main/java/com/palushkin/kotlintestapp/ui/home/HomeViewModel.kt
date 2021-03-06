/*
 * 20.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.palushkin.kotlintestapp.database.getDatabase
import com.palushkin.kotlintestapp.domain.DomainUser
import com.palushkin.kotlintestapp.network.User
import com.palushkin.kotlintestapp.network.UserApi
import com.palushkin.kotlintestapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class UserApiStatus { LOADING, ERROR, DONE }

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = Repository(database)




//    private val _properties = MutableLiveData<List<User>>()
//    val properties: LiveData<List<User>>
//        get() = _properties



    private val _status = MutableLiveData<UserApiStatus>()
    val status: LiveData<UserApiStatus>
        get() = _status

    private val _navigateToSelectedUser = MutableLiveData<DomainUser>()
    val navigateToSelectedUser: LiveData<DomainUser>
        get() = _navigateToSelectedUser

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        //getUserProperties()
        refreshDataFromNetwork()
    }


    private val _properties = repository.domainUsers
    val properties: LiveData<List<DomainUser>>
        get() = _properties



    /*private fun getUserProperties() {

        uiScope.launch {
            // TODO: 28.01.2021  если нет сети -> UserApiStatus.ERROR
            _status.value = UserApiStatus.LOADING
            try {
                //Log.i("test0", "ПРИШЕЛ_0")
                _properties.value = UserApi.retrofitService.getUsers()
                _status.value = UserApiStatus.DONE
                //Log.i("test0", "ПРИШЕЛ_1: ${_properties.value!!.last().firstName}")
            } catch (e: Exception) {
                //Log.e("test0", "ОШИБКА запроса к серверу", e)
                e.printStackTrace()
                _status.value = UserApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }*/


    private fun refreshDataFromNetwork(){

        uiScope.launch {
            // TODO: 28.01.2021  если нет сети -> UserApiStatus.ERROR
            _status.value = UserApiStatus.LOADING
            try {
                //Log.i("test0", "ПРИШЕЛ_0")
                repository.refreshEntities()
                _status.value = UserApiStatus.DONE
                //Log.i("test0", "ПРИШЕЛ_1: ${_properties.value!!.last().firstName}")
            } catch (e: Exception) {
                //Log.e("test0", "ОШИБКА запроса к серверу", e)
                e.printStackTrace()
                _status.value = UserApiStatus.ERROR
                //_properties.value = ArrayList()
            }

        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun displayUserDetails(user: DomainUser) {
        _navigateToSelectedUser.value = user
    }


    fun displayUserDetailsComplete() {
        _navigateToSelectedUser.value = null
    }

}