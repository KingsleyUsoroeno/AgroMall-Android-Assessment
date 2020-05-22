package com.techkingsley.agromall.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techkingsley.agromall.data.Farms

@Dao
interface FarmDao {

    @Query("SELECT * FROM Farms")
    fun observeFarms(): LiveData<List<Farms>>

    @Query("SELECT * FROM Farms WHERE farmID = :farmID")
    fun observeFarmsById(farmID: String): LiveData<Farms>

    @Query("SELECT * FROM Farms")
    suspend fun getFarms(): List<Farms>

    @Query("SELECT COUNT(*) FROM Farms")
    fun getCount(): LiveData<Int?>

    @Query("SELECT * FROM Farms WHERE farmID = :farmerId")
    suspend fun getFarmById(farmerId: String): Farms?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarm(farm: Farms)

    @Update
    suspend fun updateFarm(farm: Farms): Int

    @Query("DELETE FROM Farms")
    suspend fun deleteAllFarms()

    @Query("DELETE FROM Farms WHERE farmID = :farmID")
    suspend fun deleteFarmByID(farmID: String): Int

    @Query("SELECT * FROM Farms WHERE farmerId =:farmerId AND userId =:userID")
    fun observeFarmsByFarmerID(farmerId: String, userID: Int): LiveData<List<Farms>?>

    @Query("SELECT COUNT(*) FROM Farms WHERE farmerId =:farmerId AND userId =:userId")
    fun observeTotalFarmersFarmCount(farmerId: String, userId: Int): LiveData<Int?>
}