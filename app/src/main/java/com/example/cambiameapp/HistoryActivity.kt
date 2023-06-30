package com.example.cambiameapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cambiameapp.databinding.ActivityArgBinding
import com.example.cambiameapp.databinding.ActivityHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistoryActivity : AppCompatActivity() {

    public lateinit var fbDatabase : FirebaseDatabase
    lateinit var auth : FirebaseAuth
    lateinit var redDb : DatabaseReference

    data class ItemConversion(val fecha:String, val tipoConversion: String, val montoBol: Double, val montoRes: Double, val cambio: Double){

    }

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val data = ArrayList<ItemConversion>()

        fbDatabase = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        redDb = fbDatabase.getReference("InfoConversion").child(auth.currentUser?.uid.toString())


        val recv = binding.recyclerview
        recv.layoutManager = LinearLayoutManager(this)
        val listener = object:ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                for (ds: DataSnapshot in snapshot.children) {
                    val ic = ItemConversion(ds.child("Conversion").child("fecha").value.toString(),ds.child("Conversion").child("tipoConversion").value.toString(),ds.child("Conversion").child("monto").value.toString().toDouble(),ds.child("Conversion").child("res").value.toString().toDouble(),ds.child("Conversion").child("cambio").value.toString().toDouble())
                    data.add(ic)
            }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        redDb.addValueEventListener(listener)




        val adapter = CustomAdapter(data)

        recv.adapter = adapter
    }
}