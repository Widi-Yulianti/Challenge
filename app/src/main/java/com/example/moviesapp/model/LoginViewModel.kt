package com.example.moviesapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.datastore.DataStoreManager
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: DataStoreManager): ViewModel() {
    fun getUser(): LiveData<UserPreferences> {
        return pref.getUser().asLiveData()
    }

    fun setUserLogin(isLogin: Boolean) {
        viewModelScope.launch {
            pref.setUserLogin(isLogin)
        }
    }

    fun saveUser(id: String,
                 username: String,
                 password: String,
                 confirm: String,
                 address: String) {
        viewModelScope.launch {
            pref.setUser(id, username, password, confirm, address)
        }
    }
}