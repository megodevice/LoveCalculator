package com.example.lovecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.calculator_data.LoveModel
import com.example.lovecalculator.calculator_data.LovePresenter
import com.example.lovecalculator.calculator_data.LoveService
import com.example.lovecalculator.calculator_data.LoveViewModel
import com.example.lovecalculator.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment(), LoveViewModel {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val lovePresenter = LovePresenter(LoveService().api, this)
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
    }

    private fun initListeners() {
        binding.apply {
            btCalculate.setOnClickListener {
                lovePresenter.getPercentage(etFname.text.toString(), etSname.text.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showResult(loveModel: LoveModel) {
        findNavController().navigate(CalculatorFragmentDirections.actionCalculatorFragmentToResultFragment(loveModel))
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}