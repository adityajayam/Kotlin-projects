package com.example.marsphoto

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marsphoto.adapter.PhotoGridAdapter
import com.example.marsphoto.databinding.FragmentOverViewBinding
import com.example.marsphoto.model.OverviewViewModel

class OverViewFragment : Fragment() {
    private lateinit var fragmentOverViewBinding: FragmentOverViewBinding
    private val overViewViewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentOverViewBinding = FragmentOverViewBinding.inflate(inflater, container, false)
        fragmentOverViewBinding.apply {
            viewModel = overViewViewModel
            lifecycleOwner = this@OverViewFragment
        }
        fragmentOverViewBinding.imageList.adapter = PhotoGridAdapter()
        return fragmentOverViewBinding.root
    }

}