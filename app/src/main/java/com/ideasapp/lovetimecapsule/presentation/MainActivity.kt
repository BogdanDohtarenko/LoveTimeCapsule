package com.ideasapp.lovetimecapsule.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ideasapp.lovetimecapsule.R
import com.ideasapp.lovetimecapsule.databinding.ActivityMainBinding
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    private fun launchAddFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container,  AddCapsuleFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun launchListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.addCapsuleFragment,  ListCapsuleFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun launchShowFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.addCapsuleFragment,  ShowCapsuleFragment())
            .addToBackStack(null)
            .commit()
    }
}