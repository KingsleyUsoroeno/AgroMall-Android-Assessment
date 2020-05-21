package com.techkingsley.agromall.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Farms(
    @PrimaryKey @ColumnInfo(name = "farmID") var farmId: String = UUID.randomUUID().toString(),
    var userId: Int = 0,
    var farmerId: String = "",
    var farmName: String = "",
    var farmLocation: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
)

