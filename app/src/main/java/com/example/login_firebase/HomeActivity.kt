package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance() //instanciamos la base de datos


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Familiares"

        //Setup
        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")

        val recyclerView: RecyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val pacientes = ArrayList<Paciente>()

        pacientes.add(Paciente("Ayla", age = 0, R.drawable.foto1))
        pacientes.add(Paciente("Martin", age = 5, R.drawable.foto2))
        pacientes.add(Paciente("Belen", age = 39, R.drawable.foto3))

        val adapter = AdapterPaciente(pacientes)

        recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            val addIntent = Intent(this, AddActivity::class.java)
            startActivity(addIntent)

        binding.scapeButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed() //vuelve a la activity anterior

            }

        }
    }
}