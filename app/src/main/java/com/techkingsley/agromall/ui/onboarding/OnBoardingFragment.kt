package com.techkingsley.agromall.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.techkingsley.agromall.Constant
import com.techkingsley.agromall.R
import com.techkingsley.agromall.data.source.local.storage.SharedPreferenceStorage
import com.techkingsley.agromall.data.source.local.storage.Storage
import com.techkingsley.agromall.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_onboarding.*

/**
 * A simple [Fragment] subclass.
 */
class OnBoardingFragment : Fragment(R.layout.fragment_onboarding), ViewPager.OnPageChangeListener {

    private lateinit var layouts: List<Int>
    private lateinit var storage: Storage

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        storage = SharedPreferenceStorage(requireContext())
        hasOnBoarded()
        setUpOnBoarding()
    }

    private fun setUpOnBoarding() {
        layouts = arrayListOf(R.layout.layout_first_item, R.layout.layout_second_item, R.layout.layout_third_item)
        val adapter = ViewPagerAdapter(layouts)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        circularIndicator.setViewPager(viewPager)
    }

    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) =
        Unit

    override fun onPageSelected(position: Int) {
        if (position == layouts.size - 1) {
            btnNext.visibility = View.VISIBLE
            btnNext.isEnabled = true
            btnNext.setOnClickListener {
                it.findNavController().navigate(R.id.action_onBoardingFragment_to_registrationFragment)
                storage.setBoolean(Constant.HAS_ON_BOARDED, true)
            }
        } else {
            btnNext.visibility = View.GONE
            btnNext.isEnabled = false
        }
    }

    private fun hasOnBoarded() {
        val hasOnBoarded = storage.getBoolean(Constant.HAS_ON_BOARDED)
        if (hasOnBoarded) {
            return findNavController().navigate(R.id.action_onBoardingFragment_to_registrationFragment)
        }
    }
}

