package com.example.cambiameapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cambiameapp.databinding.ActivityArgBinding
import com.example.cambiameapp.databinding.ActivityChilBinding
import com.example.cambiameapp.databinding.ActivityDolBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.MonthDay

class DolActivity : AppCompatActivity() {

    data class Conversion(
        var tipoConversion: String? =null,
        var monto: Double?=null,
        var res: Double?=null,
        var fecha: String?=null,
        var usuario: String?=null,
        var cambio: Double?=null
    )

    private lateinit var binding: ActivityDolBinding

    private lateinit var fbDatabase: FirebaseDatabase
    private lateinit var refDb: DatabaseReference
    private lateinit var conversion: ChilActivity.Conversion
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        fbDatabase= FirebaseDatabase.getInstance()
        refDb=fbDatabase.getReference("InfoConversion")
        conversion = ChilActivity.Conversion()

        binding.setBtn.setOnClickListener {
            val intent = Intent(this, SetDolActivity::class.java)
            startActivity(intent)
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            var monto: Double = binding.editTextUser.text.toString().toDouble()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                conversion.fecha= MonthDay.now().toString()
            }

            conversion.monto = monto

            val receivedValue = intent.getDoubleExtra("keyDol",0.0)
            if (receivedValue != 0.0) {
                conversion.res = (monto*receivedValue)
                conversion.tipoConversion = "Dolares"
                conversion.cambio = receivedValue
                binding.montoDol.text=(monto*receivedValue).toString()
                binding.montoBolDol.text=monto.toString()
                refDb.push()
                refDb.setValue(conversion)

            }
            else {
                Toast.makeText(this, "Por favor ingrese el tipo de cambio en el engranaje", Toast.LENGTH_LONG).show()
            }
        }
    }
}