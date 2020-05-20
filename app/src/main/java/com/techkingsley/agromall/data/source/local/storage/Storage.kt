package com.techkingsley.agromall.data.source.local.storage

import com.techkingsley.agromall.data.source.User

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
    fun setBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
    fun setDefaultUser()
    fun getCurrentUser(): User
}