package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cambiameapp.databinding.ActivityArgBinding
import com.example.cambiameapp.databinding.ActivitySetBinding

class SetActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener{
            val intent = Intent(this, ArgActivity::class.java)
            intent.putExtra("keyArg", binding.editTextUser.text.toString().toDouble())
            startActivity(intent)
        }

    }
}