package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerkotlin.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val pacientes = ArrayList<Paciente>()

        pacientes.add(Paciente("Ayla", age = 0, R.drawable.foto1))
        pacientes.add(Paciente("Martin", age = 5, R.drawable.foto2))
        pacientes.add(Paciente("Belen", age = 39, R.drawable.foto3))

        val adapter = AdapterPaciente(pacientes)

        recyclerView.adapter = adapter
    }
}