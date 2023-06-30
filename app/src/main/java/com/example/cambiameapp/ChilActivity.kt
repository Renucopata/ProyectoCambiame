package com.example.cambiameapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cambiameapp.databinding.ActivityChilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.MonthDay.now


class ChilActivity : AppCompatActivity() {

    data class Conversion(
        var tipoConversion: String? =null,
        var monto: Double?=null,
        var res: Double?=null,
        var fecha: String?=null,
        var usuario: String?=null,
        var cambio: Double?=null
    )

    private lateinit var binding: ActivityChilBinding


    private lateinit var fbDatabase: FirebaseDatabase
    private lateinit var refDb: DatabaseReference
    private lateinit var conversion: Conversion
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        fbDatabase= FirebaseDatabase.getInstance()
        refDb=fbDatabase.getReference("InfoConversion").child(auth.currentUser?.uid.toString()).child("Conversion")
        conversion = Conversion()


        binding.setButton.setOnClickListener {
            val intent = Intent(this, SetChActivity::class.java)
            startActivity(intent)
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            var monto: Double = binding.editTextUser.text.toString().toDouble()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                conversion.fecha= now().toString()
            }

            conversion.monto = monto

            val receivedValue = intent.getDoubleExtra("keyCh",0.0)
            if (receivedValue != 0.0) {
                conversion.res = (monto*receivedValue)
                conversion.tipoConversion = "PesosChilenos"
                conversion.cambio = receivedValue
                binding.montoChil.text=(monto*receivedValue).toString()
                binding.montoBol.text=monto.toString()
                refDb.push()
                refDb.setValue(conversion)

            }
            else {
                Toast.makeText(this, "Por favor ingrese el tipo de cambio en el engranaje", Toast.LENGTH_LONG).show()
            }



        }
    }
}