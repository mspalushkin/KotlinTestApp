/*
 * 27.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.palushkin.kotlintestapp.network.User

class DetailViewModelFactory(
        private val user: User,
        private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(user, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}