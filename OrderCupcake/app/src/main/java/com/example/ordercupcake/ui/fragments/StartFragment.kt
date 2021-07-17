package com.example.ordercupcake.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ordercupcake.R
import com.example.ordercupcake.databinding.FragmentStartBinding
import com.example.ordercupcake.model.OrderViewModel


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val sharedOrderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.startFragment = this
    }

    fun orderCupcake(quantity: Int) {
        sharedOrderViewModel.setQuantity(quantity)
        if (sharedOrderViewModel.hasNoFlavorSet()) {
            sharedOrderViewModel.setFlavor(getString(R.string.vanilla_flavour))
        }
        val action = StartFragmentDirections.actionStartFragmentToChooseFlavour()
        findNavController().navigate(action)
    }
}