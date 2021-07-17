package com.example.ordercupcake.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ordercupcake.R
import com.example.ordercupcake.databinding.FragmentChooseFlavourBinding
import com.example.ordercupcake.model.OrderViewModel

class ChooseFlavourFragment : Fragment() {

    private lateinit var binding: FragmentChooseFlavourBinding
    private val sharedOrderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseFlavourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            orderViewModel = sharedOrderViewModel
            binding.chooseFlavourFragment = this@ChooseFlavourFragment
        }
    }

    fun callPickUpFragment() {
        val action = ChooseFlavourFragmentDirections.actionChooseFlavourToPickUpFragment()
        findNavController().navigate(action)
    }

    fun cancelOrder(){
        sharedOrderViewModel.resetOrder()
        findNavController().navigate(R.id.action_chooseFlavourFragment_to_startFragment)
    }
}