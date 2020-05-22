package com.techkingsley.agromall.data.source

import androidx.lifecycle.LiveData
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.data.Farms

interface Repository {
    suspend fun saveFarmer(farmers: Farmers)

    suspend fun saveFarms(farms: Farms)

    suspend fun deleteAllFarms()

    suspend fun deleteAllFarmers(farms: Farms)

    fun observeFarmers(): LiveData<List<Farmers>>

    fun observeFarms(): LiveData<List<Farms>>

    fun getTotalFarmers(): LiveData<Int?>

    fun getTotalFarms(): LiveData<Int?>

    fun observeFarmsByFarmerID(farmerId: String, userId: Int): LiveData<List<Farms>?>

    fun observeTotalFarmersFarmCount(farmerId: String, userId: Int): LiveData<Int?>
}