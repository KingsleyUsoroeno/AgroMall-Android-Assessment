package com.techkingsley.agromall.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.techkingsley.agromall.data.source.FarmersRepository

class DashboardViewModel(app: Application) : AndroidViewModel(app) {

    private val farmerRepository = FarmersRepository.getRepository(app)

    val observeFarmers = farmerRepository.observeFarmers()

    val getTotalFarmers = farmerRepository.getTotalFarmers()

    val getTotalFarms = farmerRepository.getTotalFarms()
}
