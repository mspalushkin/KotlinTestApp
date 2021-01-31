/*
 * 20.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palushkin.kotlintestapp.domain.DomainUser

class DetailViewModel (user: DomainUser, app: Application) : AndroidViewModel(app) {

    private val _selectedUser = MutableLiveData<DomainUser>()

    val selectedUser: LiveData<DomainUser>
        get() = _selectedUser

    init {
        _selectedUser.value = user
    }

}