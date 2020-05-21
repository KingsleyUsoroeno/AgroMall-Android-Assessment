package com.techkingsley.agromall.data.source

import androidx.lifecycle.LiveData
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.data.Farms

interface FarmersDataSource {

    fun observeFarmers(): LiveData<List<Farmers>>

    fun observeFarms(): LiveData<List<Farms>>

    suspend fun getFarmers(): List<Farmers>

    fun observeFarmers(farmerId: String): LiveData<Farmers>

    fun observeFarms(farmId: String): LiveData<Farms>

    suspend fun getFarmers(farmerId: String): Farmers?

    suspend fun getFarms(farmId: String): Farms?

    suspend fun saveFarmers(farmers: Farmers)

    suspend fun saveFarms(farms: Farms)

    suspend fun deleteAllFarmers()

    suspend fun deleteAllFarms()

    suspend fun deleteFarm(farms: Farms)

    suspend fun deleteFarmers(farms: Farms)

    fun getAllFarmsByFarmerID(farmerId: String, userId: Int): LiveData<List<Farms>?>

    fun getTotalFarmers(): LiveData<Int?>?

    fun getTotalFarms(): LiveData<Int?>?

}