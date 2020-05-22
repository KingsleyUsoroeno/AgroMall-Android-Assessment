package com.techkingsley.agromall.ui.farms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.techkingsley.agromall.data.Farms
import com.techkingsley.agromall.data.source.FarmersRepository

class FarmsViewModel(private val app: Application) : AndroidViewModel(app) {

    private val farmersRepository = FarmersRepository.getRepository(app)

    fun observerFarmsByFarmerID(farmerId: String, userId: Int): LiveData<List<Farms>?> {
        return farmersRepository.observeFarmsByFarmerID(farmerId, userId)
    }

    fun observeTotalFarmersFarmCount(farmerId: String, userId: Int): LiveData<Int?> {
        return farmersRepository.observeTotalFarmersFarmCount(farmerId, userId)
    }

}
