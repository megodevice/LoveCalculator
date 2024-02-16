package com.example.lovecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lovecalculator.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val args: ResultFragmentArgs by navArgs()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        binding.btTryAgain.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView() {
        args.loveModel.apply {
            binding.apply {
                tvFname.text = fname
                tvSname.text = sname
                val percent = "$percentage%"
                tvPercentage.text = percent
            }
        }
    }

    private fun initBackStack() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}