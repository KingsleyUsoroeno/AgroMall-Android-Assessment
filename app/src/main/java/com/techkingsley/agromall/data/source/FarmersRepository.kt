package com.techkingsley.agromall.data.source

import android.app.Application
import androidx.lifecycle.LiveData
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.data.Farms
import com.techkingsley.agromall.data.source.local.database.FarmersLocalDataSource
import com.techkingsley.agromall.data.source.local.database.FarmersLocalDatabase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FarmersRepository private constructor(private val farmersLocalDataSource: FarmersLocalDataSource) : Repository {

    companion object {
        @Volatile
        private var INSTANCE: FarmersRepository? = null

        fun getRepository(app: Application): FarmersRepository {
            return INSTANCE ?: synchronized(this) {
                val farmDB = FarmersLocalDatabase.database(app)

                FarmersRepository(FarmersLocalDataSource(farmDB.farmsDao(), farmDB.farmersDao())).also {
                    INSTANCE = it
                }
            }
        }
    }

    override suspend fun saveFarmer(farmers: Farmers) {
        coroutineScope {
            launch { farmersLocalDataSource.saveFarmers(farmers) }
        }
    }

    override suspend fun saveFarms(farms: Farms) {
        coroutineScope {
            launch { farmersLocalDataSource.saveFarms(farms) }
        }
    }

    override suspend fun deleteAllFarms() {
        coroutineScope {
            launch { farmersLocalDataSource.deleteAllFarmers() }
        }
    }

    override suspend fun deleteAllFarmers(farms: Farms) {
        coroutineScope {
            launch { farmersLocalDataSource.deleteAllFarmers() }
        }
    }

    override fun observeFarmers(): LiveData<List<Farmers>> {
        return farmersLocalDataSource.observeFarmers()
    }

    override fun observeFarms(): LiveData<List<Farms>> {
        return farmersLocalDataSource.observeFarms()
    }

    override fun getTotalFarmers(): LiveData<Int?> {
        return farmersLocalDataSource.getTotalFarmers()
    }

    override fun getTotalFarms(): LiveData<Int?> {
        return farmersLocalDataSource.getTotalFarms()
    }

    override fun observeFarmsByFarmerID(farmerId: String, userId: Int): LiveData<List<Farms>?> {
        return farmersLocalDataSource.getAllFarmsByFarmerID(farmerId, userId)
    }

    override fun observeTotalFarmersFarmCount(farmerId: String, userId: Int): LiveData<Int?> {
        return farmersLocalDataSource.observeTotalFarmersFarmCount(farmerId, userId)
    }
}