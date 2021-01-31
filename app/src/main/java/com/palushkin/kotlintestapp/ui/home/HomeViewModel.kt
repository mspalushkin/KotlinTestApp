/*
 * 20.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.palushkin.kotlintestapp.network.User
import com.palushkin.kotlintestapp.network.UserApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class UserApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {

    private val _properties = MutableLiveData<List<User>>()
    val properties: LiveData<List<User>>
        get() = _properties

    private val _status = MutableLiveData<UserApiStatus>()
    val status: LiveData<UserApiStatus>
        get() = _status

    private val _navigateToSelectedUser = MutableLiveData<User>()
    val navigateToSelectedUser: LiveData<User>
        get() = _navigateToSelectedUser

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getUserProperties()
    }

    private fun getUserProperties() {

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
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun displayUserDetails(user: User) {
        _navigateToSelectedUser.value = user
    }


    fun displayUserDetailsComplete() {
        _navigateToSelectedUser.value = null
    }

}