package com.techkingsley.agromall.data.source.local.storage

import android.content.Context
import com.techkingsley.agromall.Constant
import com.techkingsley.agromall.data.source.User

class SharedPreferenceStorage(private val context: Context) : Storage {

    private val sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    init {
        setDefaultUser()
    }

    override fun setString(key: String, value: String) {
        if (key.isEmpty()) return
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    override fun setBoolean(key: String, value: Boolean) {
        if (key.isEmpty()) return
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun setDefaultUser() {
        setString(Constant.DEFAULT_USER_EMAIL, "test@theagromall.com")
        setString(Constant.DEFAULT_USER_PASSWORD, "password")
    }

    override fun getCurrentUser(): User {
        val email = getString(Constant.DEFAULT_USER_EMAIL)
        val password = getString(Constant.DEFAULT_USER_PASSWORD)
        return User(1, email, password)
    }

}