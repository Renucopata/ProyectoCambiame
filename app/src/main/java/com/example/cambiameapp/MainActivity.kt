package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.cambiameapp.databinding.ActivityLoginBinding
import com.example.cambiameapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnArg.setOnClickListener {
            val intent = Intent(this, ArgActivity::class.java)
            startActivity(intent)
        }

        binding.btnChil.setOnClickListener {
            val intent = Intent(this, ChilActivity::class.java)
            startActivity(intent)
        }


        binding.btnSol.setOnClickListener {
            val intent = Intent(this, SolActivity::class.java)
            startActivity(intent)
        }

        binding.btnDol.setOnClickListener {
            val intent = Intent(this, DolActivity::class.java)
            startActivity(intent)
        }

        binding.btnOtro.setOnClickListener {
            val intent = Intent(this, OtrosActivity::class.java)
            startActivity(intent)
        }
    }
}