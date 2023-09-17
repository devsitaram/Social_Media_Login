package com.edu.socialmediallogin.presentation.viewmodel.signin

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.domain.model.GoogleUserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch

class SignInGoogleViewModel(context: Context) : ViewModel() {

    private var _userState = MutableLiveData<GoogleUserModel?>()
    val googleUser: MutableLiveData<GoogleUserModel?> = _userState
    private var _loadingState = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loadingState

    init {
        checkSignedInUser(context.applicationContext)
    }

    fun fetchSingInUser(email: String?, name: String?) {
        _loadingState.value = true
        viewModelScope.launch {
            _userState.value = GoogleUserModel(email = email, name = name,)
        }
        _loadingState.value = false
    }

    private fun checkSignedInUser(context: Context) {
        _loadingState.value = true
        val gsa = GoogleSignIn.getLastSignedInAccount(context)
        if (gsa != null) {
            _userState.value = GoogleUserModel(
                email = gsa.email,
                name = gsa.displayName,
            )
        }
        _loadingState.value = false
    }

    fun hideLoading() {
        _loadingState.value = false
    }

    fun showLoading() {
        _loadingState.value = true
    }

    @Suppress("UNCHECKED_CAST")
    class SignInGoogleViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignInGoogleViewModel::class.java)) {
                return SignInGoogleViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}