package com.techkingsley.agromall.ui.registration

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techkingsley.agromall.base.SingleEventLiveData
import com.techkingsley.agromall.base.Utils
import com.techkingsley.agromall.data.Farmers
import com.techkingsley.agromall.data.source.FarmersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(private val app: Application) : AndroidViewModel(app) {

    //observable fields with DataBinding
    val fullNameLiveData = MutableLiveData("")
    val emailLiveData = MutableLiveData("")
    val phoneNumberLiveData = MutableLiveData("")
    val homeAddressLiveData = MutableLiveData("")
    val photoUrlLiveData = MutableLiveData("")

    //Error Observable fields
    var errorFullNameLiveData = MutableLiveData("")
    var errorEmailLiveData = MutableLiveData("")
    var errorPhoneNumberLiveData = MutableLiveData("")
    var errorHomeAddressLiveData = MutableLiveData("")
    var errorPhotoUrlLiveData = SingleEventLiveData<String>()

    private val isSuccessEvent = SingleEventLiveData<Boolean>()

    val feedBackLiveData: SingleEventLiveData<Boolean>
        get() = isSuccessEvent

    private val farmersRepository = FarmersRepository.getRepository(app)

    fun registerFarmer() {

        if (!validateFields(fullNameLiveData, errorFullNameLiveData, "Full Name") || !validateFields(emailLiveData, errorEmailLiveData, "Email")
            || !validateFields(phoneNumberLiveData, errorPhoneNumberLiveData, "Phone Number") || !validateFields(homeAddressLiveData, errorHomeAddressLiveData, "Home Address")
        ) {
            return
        }

        if (!Utils.isEmailValid(emailLiveData.value!!)) {
            errorEmailLiveData.value = "Email is not valid"
            return
        }

        if (photoUrlLiveData.value?.isEmpty()!!) {
            errorPhotoUrlLiveData.value = "your image is required"
            return
        }

        val fullName = fullNameLiveData.value!!.trim()
        val email = emailLiveData.value!!.trim()
        val phoneNumber = phoneNumberLiveData.value!!.trim()
        val homeAddress = homeAddressLiveData.value!!.trim()
        val imageUrl = photoUrlLiveData.value!!.trim()
        val farmer = Farmers(fullName = fullName, email = email, phoneNumber = phoneNumber, homeAddress = homeAddress, imageUrl = imageUrl)
        Log.i(TAG, "farmers value is $farmer")
        viewModelScope.launch(Dispatchers.IO) { farmersRepository.saveFarmer(farmer) }
        isSuccessEvent.value = true
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
        private const val TAG = "RegistrationViewModel"
    }

}
