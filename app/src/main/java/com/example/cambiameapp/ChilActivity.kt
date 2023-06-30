package com.example.cambiameapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.cambiameapp.databinding.ActivityChilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

    private lateinit var montoEt: EditText
    private lateinit var chilesTv: TextView
    private lateinit var bolisTv: TextView

    private lateinit var fbDatabase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var refDb: DatabaseReference
    private lateinit var conversion: Conversion

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityChilBinding.inflate(layoutInflater)

        auth=FirebaseAuth.getInstance()
        setContentView(binding.root)
        fbDatabase= FirebaseDatabase.getInstance()
            refDb=fbDatabase.getReference("InfoConversion").child(auth.currentUser?.uid.toString()).child("Conversion")
        conversion = Conversion()

        montoEt = binding.editTextUser
        chilesTv = binding.montoChil
        bolisTv = binding.montoBol
        var cambio: Double =refDb.child("InfoConversion").child(auth.currentUser.toString()).child("TipoConver").child("cambioChile").get().toString().toDouble()

        binding.setButton.setOnClickListener {
            val intent = Intent(this, SetActivity::class.java)
            startActivity(intent)
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            var monto: Double = montoEt.text.toString().toDouble()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                conversion.fecha= now().toString()
            }
            conversion.monto = monto
            conversion.res = (monto*cambio)
            conversion.tipoConversion = "PesosChilenos"
            conversion.cambio = cambio

            chilesTv.text=(monto*cambio).toString()
            bolisTv.text=monto.toString()
            refDb.push()
            refDb.setValue(conversion)

        }
    }
}