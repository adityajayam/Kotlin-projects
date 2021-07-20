package com.example.ordercupcake.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ordercupcake.R
import com.example.ordercupcake.databinding.FragmentPickupBinding
import com.example.ordercupcake.model.OrderViewModel

class PickUpFragment : Fragment() {

    private lateinit var binding: FragmentPickupBinding
    private val sharedOrderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPickupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            orderViewModel = sharedOrderViewModel
            lifecycleOwner = viewLifecycleOwner
            binding.pickUpFragment = this@PickUpFragment
        }
    }

    fun callSummaryFragment() {
        val action = PickUpFragmentDirections.actionPickUpFragmentToSummaryFragment()
        findNavController().navigate(action)
    }

    fun cancelOrder(){
        sharedOrderViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickUpFragment_to_startFragment)
    }
}