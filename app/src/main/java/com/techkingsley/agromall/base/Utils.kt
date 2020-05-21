package com.techkingsley.agromall.base

import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
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

    fun getImagePathFromUri(uri: Uri, activity: Activity): String? {
        val cursor = activity.contentResolver.query(uri, null, null, null, null)
        return if (cursor == null) {
            uri.path
        } else {
            val idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(idx)
        }
    }
}