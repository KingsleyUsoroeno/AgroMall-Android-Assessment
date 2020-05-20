package com.techkingsley.agromall.ui.authentication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techkingsley.agromall.Constant
import com.techkingsley.agromall.base.SingleEventLiveData
import com.techkingsley.agromall.base.Utils.isEmailValid
import com.techkingsley.agromall.data.source.local.storage.SharedPreferenceStorage

class LoginViewModel(private val app: Application) : AndroidViewModel(app) {

    //observable fields with DataBinding
    val emailLiveData = MutableLiveData("")
    val passwordLiveData = MutableLiveData("")

    //Error Observable fields
    var errorEmailLiveData = MutableLiveData("")
    var errorPasswordLiveData = MutableLiveData("")

    private val errorMsgMutableLiveData = SingleEventLiveData<String>()

    val feedbackMessageLiveData: SingleEventLiveData<String>
        get() = errorMsgMutableLiveData

    private val isSuccess = SingleEventLiveData<Boolean>()

    val successLiveData: SingleEventLiveData<Boolean>
        get() = isSuccess

    private val storage = SharedPreferenceStorage(app.applicationContext)

    fun loginUser() {

        val email = emailLiveData.value?.trim()
        val password = passwordLiveData.value?.trim()

        Log.i(TAG, "password is $password and email is $email")

        if (!validateFields(emailLiveData, errorEmailLiveData, "Email") || !validateFields(passwordLiveData, errorPasswordLiveData, "password")) {
            return
        }

        if (!isEmailValid(emailLiveData.value!!)) {
            errorEmailLiveData.value = "Email is not valid"
            return
        }

        val currentUser = storage.getCurrentUser()
        if (email != currentUser.email || password != currentUser.password) {
            errorMsgMutableLiveData.value = "User does not exit"
            isSuccess.value = false

        } else if (email == currentUser.email && password == currentUser.password) {
            Log.i(TAG, "current user is $currentUser")
            errorMsgMutableLiveData.value = "Welcome back"
            storage.setBoolean(Constant.HAS_LOGGED_IN, true)
            isSuccess.value = true
        }
    }

    private fun validateFields(key: MutableLiveData<String>, errorLiveData: MutableLiveData<String>, field: String): Boolean {
        return if (key.value!!.trim().isEmpty()) {
            errorLiveData.value = "$field is required"
            false
        } else {
            errorLiveData.value = null
            true
        }
    }

    fun hasLoggedIn() = storage.getBoolean(Constant.HAS_LOGGED_IN)

    companion object {
        private const val TAG = "LoginViewModel"
    }

}