package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cambiameapp.databinding.ActivityArgBinding
import com.example.cambiameapp.databinding.ActivityChilBinding
import com.example.cambiameapp.databinding.ActivityDolBinding

class DolActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setBtn.setOnClickListener {
            val intent = Intent(this, SetActivity::class.java)
            startActivity(intent)
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}