package com.example.lovecalculator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.databinding.FragmentOnboardingGetStartedBinding
import com.example.lovecalculator.utils.AppSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingGetStartedFragment : Fragment() {

    private var _binding: FragmentOnboardingGetStartedBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingGetStartedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!appSharedPreferences.getOnboarding())
            findNavController().navigate(OnboardingGetStartedFragmentDirections.actionOnboardingGetStartedFragmentToCalculatorFragment())
        initListeners()
    }

    private fun initListeners() {
        binding.btGetStarted.setOnClickListener {
            findNavController().navigate(OnboardingGetStartedFragmentDirections.actionOnboardingGetStartedFragmentToOnboardingFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}