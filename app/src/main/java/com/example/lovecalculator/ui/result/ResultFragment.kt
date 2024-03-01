package com.example.lovecalculator.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lovecalculator.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val args: ResultFragmentArgs by navArgs()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackStack()
        initView()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            btTryAgain.setOnClickListener {
                navigateToMain()
            }

            tvHistory.setOnClickListener {
                findNavController().navigate(ResultFragmentDirections.actionResultFragmentToHistoryFragment())
            }

            tvHome.setOnClickListener {
                navigateToMain()
            }
        }
    }

    private fun initView() {
        args.loveModel.apply {
            binding.apply {
                tvFirstName.text = firstName
                tvSecondName.text = secondName
                val percent = "$percentage%"
                tvPercentage.text = percent
            }
        }
    }

    private fun initBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToMain()
                }
            })
    }

    private fun navigateToMain() {
        findNavController().navigate(ResultFragmentDirections.actionResultFragmentToCalculatorFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}