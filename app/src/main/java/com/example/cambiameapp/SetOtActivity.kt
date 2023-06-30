package com.example.cambiameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cambiameapp.databinding.ActivitySetBinding
import com.example.cambiameapp.databinding.ActivitySetDolBinding
import com.example.cambiameapp.databinding.ActivitySetOtBinding

class SetOtActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetOtBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetOtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener{
            val intent = Intent(this, OtrosActivity::class.java)
            intent.putExtra("keyOtr", binding.editTextUser.text.toString().toDouble())
            startActivity(intent)
        }
    }
}