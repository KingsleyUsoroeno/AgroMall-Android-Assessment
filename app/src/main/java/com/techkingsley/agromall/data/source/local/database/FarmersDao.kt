package com.techkingsley.agromall.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techkingsley.agromall.data.Farmers


@Dao
interface FarmersDao {

    @Query("SELECT * FROM Farmers")
    fun observeFarmers(): LiveData<List<Farmers>>

    @Query("SELECT * FROM Farmers WHERE farmerID = :farmerId")
    fun observeFarmersById(farmerId: String): LiveData<Farmers>

    @Query("SELECT * FROM Farmers")
    suspend fun getFarmers(): List<Farmers>

    @Query("SELECT COUNT(*) FROM farmers")
    fun getCount(): LiveData<Int?>

    @Query("SELECT * FROM Farmers WHERE farmerID = :farmerId")
    suspend fun getFarmerById(farmerId: String): Farmers?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmer(farmer: Farmers)

    @Update
    suspend fun updateFarmer(farmer: Farmers): Int


    @Query("DELETE FROM Farmers")
    suspend fun deleteAllFarmers()


    @Query("DELETE FROM Farmers WHERE farmerID = :farmerId")
    suspend fun deleteFarmerByID(farmerId: String): Int
}