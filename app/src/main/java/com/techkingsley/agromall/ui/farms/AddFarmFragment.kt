package com.techkingsley.agromall.ui.farms

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.techkingsley.agromall.BuildConfig
import com.techkingsley.agromall.R
import com.techkingsley.agromall.databinding.AddFarmFragmentBinding


class AddFarmFragment : Fragment(R.layout.add_farm_fragment) {

    companion object {
        fun newInstance() = AddFarmFragment()
        private const val TAG = "AddFarmFragment"
        private const val AUTOCOMPLETE_REQUEST_CODE = 200
    }

    private val viewModel: AddFarmViewModel by viewModels()
    private lateinit var viewBinding: AddFarmFragmentBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs: AddFarmFragmentArgs by navArgs()
        val farmerId = safeArgs.farmerId

        viewBinding = AddFarmFragmentBinding.bind(requireView())
        viewBinding.addFarmVm = viewModel
        viewBinding.userId = 1
        viewBinding.farmerId = farmerId
        viewBinding.lifecycleOwner = this

        viewBinding.edtFarmLocation.setOnClickListener {
            initPlacesApi()
        }

        viewModel.successLiveData.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(this.requireContext(), "Farm Registered Successfully", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addFarmFragment_to_dashboardFragment)
            }
        })
    }

    // Add documentation for reviewer to know how to get an ApiKey
    private fun initPlacesApi() {
        Log.i(TAG, "initPlacesApi() Called")
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), BuildConfig.GOOGLE_MAP_API_KEY)
        }
        val placesClient = Places.createClient(requireContext())
        //placesClient.fetchPhoto(FetchPhotoRequest.newInstance())
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        // Start the autocomplete intent.
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .setHint("Where's you Farm located")
            .build(requireContext())
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                val place: Place = Autocomplete.getPlaceFromIntent(data)
                viewModel.farmsLocationLiveData.value = place.address
                viewModel.farmCoordinatesLiveData.value = place.latLng
                Log.i(TAG, "Place: " + place.name.toString() + ", " + place.latLng)
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // Handle the error.
                val status: Status = Autocomplete.getStatusFromIntent(data!!)
                Log.i(TAG, "${status.statusMessage}")
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
                Log.i(TAG, "user cancelled the operation")
            }
        }
    }
}
