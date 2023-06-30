package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cambiameapp.databinding.ActivitySetBinding
import com.example.cambiameapp.databinding.ActivitySetOtBinding
import com.example.cambiameapp.databinding.ActivitySetPeBinding

class SetPeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetPeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetPeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener{
            val intent = Intent(this, SolActivity::class.java)
            intent.putExtra("keyPe", binding.editTextUser.text.toString().toDouble())
            startActivity(intent)
        }
    }
}