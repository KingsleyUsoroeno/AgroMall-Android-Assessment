package com.techkingsley.agromall.data.source.local.database

import androidx.lifecycle.LiveData
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.data.Farms
import com.techkingsley.agromall.data.source.FarmersDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FarmersLocalDataSource internal constructor(
    private val farmsDao: FarmDao, private val farmersDao: FarmersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FarmersDataSource {


    override fun observeFarmers(): LiveData<List<Farmers>> {
        return farmersDao.observeFarmers()
    }

    override fun observeFarmers(farmerId: String): LiveData<Farmers> {
        return farmersDao.observeFarmersById(farmerId)
    }

    override fun observeFarms(): LiveData<List<Farms>> {
        return farmsDao.observeFarms()
    }

    override fun observeFarms(farmId: String): LiveData<Farms> {
        return farmsDao.observeFarmsById(farmId)
    }

    override suspend fun getFarmers(): List<Farmers> = withContext(ioDispatcher) {
        return@withContext farmersDao.getFarmers()
    }

    override suspend fun getFarmers(farmerId: String): Farmers? = withContext(ioDispatcher) {
        return@withContext farmersDao.getFarmerById(farmerId)
    }

    override suspend fun getFarms(farmId: String): Farms? = withContext(ioDispatcher) {
        return@withContext farmsDao.getFarmById(farmId)
    }

    override suspend fun saveFarmers(farmers: Farmers) {
        withContext(ioDispatcher) {
            farmersDao.insertFarmer(farmers)
        }
    }

    override suspend fun saveFarms(farms: Farms) {
        withContext(ioDispatcher) {
            farmsDao.insertFarm(farms)
        }
    }

    override suspend fun deleteAllFarmers() {
        withContext(ioDispatcher) {
            farmersDao.deleteAllFarmers()
        }
    }

    override suspend fun deleteAllFarms() {
        withContext(ioDispatcher) {
            farmsDao.deleteAllFarms()
        }
    }

    override suspend fun deleteFarm(farms: Farms) {
        withContext(ioDispatcher) {
            farmsDao.deleteFarmByID(farms.farmId)
        }
    }

    override suspend fun deleteFarmers(farms: Farms) {
        withContext(ioDispatcher) {
            farmersDao.deleteFarmerByID(farms.farmerId)
        }
    }

    override fun getAllFarmsByFarmerID(farmerId: String, userId: Int): LiveData<List<Farms>?> {
        return farmsDao.observeFarmsByFarmerID(farmerId, userId)
    }

    override fun getTotalFarmers(): LiveData<Int?> {
        return farmersDao.getCount()
    }

    override fun getTotalFarms(): LiveData<Int?> {
        return farmsDao.getCount()
    }

    override fun observeTotalFarmersFarmCount(farmerId: String, userId: Int): LiveData<Int?> {
        return farmsDao.observeTotalFarmersFarmCount(farmerId, userId)
    }

}