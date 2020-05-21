package com.techkingsley.agromall.ui.farms

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.techkingsley.agromall.base.SingleEventLiveData
import com.techkingsley.agromall.data.Farms
import com.techkingsley.agromall.data.source.FarmersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFarmViewModel(private val app: Application) : AndroidViewModel(app) {

    //observable fields with DataBinding
    val farmNameLiveData = MutableLiveData("")
    val farmsLocationLiveData = MutableLiveData("")
    val farmCoordinatesLiveData = MutableLiveData<LatLng>()

    //Error Observable fields
    var errorFarmNameLiveData = MutableLiveData("")
    var errorFarmLocationLiveData = MutableLiveData("")

    private val isSuccess = SingleEventLiveData<Boolean>()

    val successLiveData: SingleEventLiveData<Boolean>
        get() = isSuccess

    private val farmersRepository = FarmersRepository.getRepository(app)

    fun registerFarm(farmerId: String, userId: Int) {
        if (!validateFields(farmNameLiveData, errorFarmNameLiveData, "Farm Name") || !validateFields(farmsLocationLiveData, errorFarmLocationLiveData, "Farm's Location")) {
            return
        }

        val farmName = farmNameLiveData.value!!.trim()
        val farmLocation = farmsLocationLiveData.value!!.trim()
        val farmLongitude = farmCoordinatesLiveData.value!!.longitude
        val farmLatitude = farmCoordinatesLiveData.value!!.latitude

        val farm = Farms(userId = userId, farmerId = farmerId, farmName = farmName, farmLocation = farmLocation, latitude = farmLatitude, longitude = farmLongitude)
        Log.i("AddFarmViewModel", "farm $farm")
        viewModelScope.launch(Dispatchers.IO) { farmersRepository.saveFarms(farm) }
        isSuccess.value = true
    }

    private fun validateFields(key: MutableLiveData<String>, errorLiveData: MutableLiveData<String>, field: String): Boolean {
        return if (key.value!!.trim().isEmpty()) {
            errorLiveData.value = "$field is required"
            false
        } else {
            errorLiveData.value = null
            true
        }
    }

    companion object {
        const val TEST_LATITUDE = 6.428102
        const val TEST_LONGITUDE = 3.469162
    }
}
