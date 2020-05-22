package com.techkingsley.agromall.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class Farms(
    @PrimaryKey @ColumnInfo(name = "farmID") var farmId: String = UUID.randomUUID().toString(),
    var userId: Int = 0,
    var farmerId: String = "",
    var farmName: String = "",
    var farmLocation: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) : Parcelable

