package com.techkingsley.agromall.base

import com.techkingsley.agromall.Constant
import java.util.regex.Pattern

object Utils {

    fun isEmailValid(email: String): Boolean {
        return if (email.isNotEmpty()) {
            val pattern = Pattern.compile(Constant.EMAIL_PATTERN)
            pattern.matcher(email.trim()).matches()
        } else {
            false
        }
    }
}