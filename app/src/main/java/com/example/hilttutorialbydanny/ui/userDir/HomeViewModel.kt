package com.example.hilttutorialbydanny.ui.userDir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilttutorialbydanny.data.network.Resource
import com.example.hilttutorialbydanny.data.repo.UserRepository
import com.example.hilttutorialbydanny.data.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser(id: Int) = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser(id)
    }
}