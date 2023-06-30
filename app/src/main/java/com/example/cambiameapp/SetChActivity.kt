package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cambiameapp.databinding.ActivitySetBinding
import com.example.cambiameapp.databinding.ActivitySetChBinding

class SetChActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetChBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetChBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener{
            val intent = Intent(this, ChilActivity::class.java)
            intent.putExtra("keyCh", binding.editTextUser.text.toString().toDouble())
            startActivity(intent)
        }
    }
}