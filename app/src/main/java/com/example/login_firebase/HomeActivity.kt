package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType {
    BASIC
    //SE SEGUIRÁN AÑADIENDO PROVEEDORES
}

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance() //instanciamos la base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup
        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email = email ?: "", provider = provider ?: "")
    }

    private fun setup(email: String, provider: String) {

        title = "Inicio"
        binding.emailTextView.text = email
        binding.providerTextView.text = provider

        binding.logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed() //vuelve a la activity anterior
        }


        binding.saveButton.setOnClickListener {
            db.collection("users").document(email).set(hashMapOf("provider" to provider,
                    "cita" to binding.adressTextView.text.toString(),
                    "hospital" to binding.phoneTextView.text.toString()))

        }
        binding.getButton.setOnClickListener {
            db.collection("users").document(email).get().addOnSuccessListener {
                binding.adressTextView.setText(it.get("adress") as String?)
                binding.phoneTextView.setText(it.get("phone") as String?)
            }

        }
        binding.deleteButton.setOnClickListener {
            db.collection("users").document(email).delete()

        }

    }
}