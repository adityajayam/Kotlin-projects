package com.example.unscramble.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unscramble.R
import com.example.unscramble.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}