package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cambiameapp.databinding.ActivitySetChBinding
import com.example.cambiameapp.databinding.ActivitySetDolBinding

class SetDolActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetDolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetDolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener{
            val intent = Intent(this, DolActivity::class.java)
            intent.putExtra("keyDol", binding.editTextUser.text.toString().toDouble())
            startActivity(intent)
        }
    }
}