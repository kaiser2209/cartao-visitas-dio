package me.dio.cartaovisitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dio.cartaovisitas.R
import me.dio.cartaovisitas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}