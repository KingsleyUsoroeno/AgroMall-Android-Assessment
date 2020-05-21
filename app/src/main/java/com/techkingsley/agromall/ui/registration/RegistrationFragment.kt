package com.techkingsley.agromall.ui.registration

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.techkingsley.agromall.Constant
import com.techkingsley.agromall.R
import com.techkingsley.agromall.base.Utils
import com.techkingsley.agromall.data.source.local.storage.SharedPreferenceStorage
import com.techkingsley.agromall.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment(R.layout.registration_fragment) {

    private lateinit var viewBinding: RegistrationFragmentBinding
    private val viewModel: RegistrationViewModel by viewModels()
    private lateinit var storage: SharedPreferenceStorage
    private var hasFarmer = false

    companion object {
        fun newInstance() = RegistrationFragment()
        private const val GALLERY_IMAGE_REQUEST = 23
        private const val STORAGE_PERMISSION_CODE = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding = RegistrationFragmentBinding.bind(requireView())
        storage = SharedPreferenceStorage(requireContext())
        hasFarmer = storage.getBoolean(Constant.HAS_REGISTERED_FARMER)
        //hasFarmer()

//        val fromDashboard = arguments?.getBoolean("FromDashBoard")
//        fromDashboard?.let {
//            if (it) {
//                Log.i("Registration Fragment", "should execute called")
//                hasFarmer(shouldExecute = false)
//            }
//        }

        viewBinding.registrationViewModel = viewModel
        viewBinding.lifecycleOwner = this
        viewBinding.imageIcon.setOnClickListener {
            openGalleryForImage()
        }

        viewModel.errorPhotoUrlLiveData.observe(this, Observer {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.feedBackLiveData.observe(this, Observer {
            if (it) {
                storage.setBoolean(Constant.HAS_REGISTERED_FARMER, true)
                findNavController().navigate(R.id.action_registrationFragment_to_dashboardFragment)
                Toast.makeText(requireContext(), "farmer registered successfully", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (hasFarmer) {
            if (item.itemId == R.id.dashboardFragment) {
                findNavController().navigate(R.id.action_registrationFragment_to_dashboardFragment)
            }
        } else {
            Toast.makeText(requireContext(), "Please register a farmer", Toast.LENGTH_LONG).show()
        }

        /*use these approach for a more rather different situation*/
//        return item.onNavDestinationSelected(Navigation.findNavController(this.requireActivity(), R.id.nav_host))
//                || super.onOptionsItemSelected(item)
        return true
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
    }

    private fun openGalleryForImage() {
        if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_IMAGE_REQUEST)
        } else {
            requestPermissions()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_IMAGE_REQUEST) {
            viewModel.photoUrlLiveData.value = Utils.getImagePathFromUri(data?.data!!, this.requireActivity())
            viewBinding.imgContactImage.setImageURI(data.data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE && grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this.requireContext(), "Cant access your files without permission", Toast.LENGTH_LONG).show()
        }
    }

    private fun hasFarmer() {
        if (hasFarmer) {
            findNavController().navigate(R.id.action_registrationFragment_to_dashboardFragment)
        }
    }
}
