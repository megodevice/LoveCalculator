package com.example.lovecalculator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.databinding.FragmentOnboardingBinding
import com.example.lovecalculator.utils.AppSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private val fragments = listOf(
        OnboardingFirstFragment(),
        OnboardingSecondFragment(),
        OnboardingThirdFragment()
    )

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wormDotsIndicator = binding.wdiOnboarding
        val viewPager = binding.vp2Onboarding
        val adapter = VP2OnboardingAdapter(childFragmentManager, lifecycle, fragments)
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
        wormDotsIndicator.dotsClickable = false
        wormDotsIndicator.attachTo(viewPager)
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            vp2Onboarding.setOnClickListener {
                if (vp2Onboarding.currentItem < fragments.size - 1) {
                    vp2Onboarding.currentItem++
                }
                else {
                    appSharedPreferences.saveOnboarding(false)
                    findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToCalculatorFragment())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}