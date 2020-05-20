package com.techkingsley.agromall.data.source.local.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}