package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cambiameapp.databinding.ActivityChilBinding

class ChilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setButton.setOnClickListener {
            val intent = Intent(this, SetActivity::class.java)
            startActivity(intent)
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}