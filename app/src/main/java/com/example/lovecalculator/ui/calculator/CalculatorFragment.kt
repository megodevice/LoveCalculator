package com.example.lovecalculator.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.databinding.FragmentCalculatorBinding
import com.example.lovecalculator.utils.AppSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculatorViewModel by viewModels()


    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        observe()
    }

    private fun observe() {
        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.result.observe(viewLifecycleOwner) {
            findNavController().navigate(
                CalculatorFragmentDirections.actionCalculatorFragmentToResultFragment(it)
            )
        }
    }

    private fun initListeners() {
        binding.apply {
            btCalculate.setOnClickListener {
                viewModel.getPercentage(etFname.text.toString(), etSname.text.toString())
            }

            tvHistory.setOnClickListener {
                findNavController().navigate(CalculatorFragmentDirections.actionCalculatorFragmentToHistoryFragment())
            }
            ivImage.setOnClickListener {
                appSharedPreferences.saveOnboarding(true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}