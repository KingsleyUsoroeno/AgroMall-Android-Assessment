package com.techkingsley.agromall.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "farmers", indices = [Index(value = ["phoneNumber", "email"], unique = true)])
data class Farmers @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "farmerID") var id: String = UUID.randomUUID().toString(),
    var fullName: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var homeAddress: String = "",
    var imageUrl: String = ""
)