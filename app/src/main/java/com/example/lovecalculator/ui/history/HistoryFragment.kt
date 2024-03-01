package com.example.lovecalculator.ui.history

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.R
import com.example.lovecalculator.databinding.FragmentHistoryBinding
import com.example.lovecalculator.remote.LoveModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null

    private val viewModel: HistoryViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackStack()
        initListeners()
        val adapter = HistoryRVAdapter { loveModel ->
            changeOrDeleteItem(loveModel)
        }
        binding.rvHistory.adapter = adapter
        viewModel.history.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun changeOrDeleteItem(loveModel: LoveModel) {
        val numberPicker = NumberPicker(context)
        numberPicker.minValue = 0
        numberPicker.maxValue = 100
        numberPicker.value = loveModel.percentage.toInt()
        AlertDialog.Builder(context).setView(numberPicker)
            .setPositiveButton(R.string.save) { _, _ ->
                viewModel.updateItem(loveModel.copy(percentage = numberPicker.value.toString()))
            }.setNegativeButton(R.string.cancel) { _, _ ->
                return@setNegativeButton
            }.setNeutralButton(R.string.delete) { _, _ ->
                viewModel.deleteItem(loveModel)
            }.create().show()
    }

    private fun initListeners() {
        binding.tvHome.setOnClickListener {
            navigateToMain()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initBackStack() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToMain()
                }
            })
    }

    private fun navigateToMain() {
        findNavController().navigate(HistoryFragmentDirections.actionHistoryFragmentToCalculatorFragment())
    }


}