package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType {
    BASIC
    //SE SEGUIRÁN AÑADIENDO PROVEEDORES
}

private lateinit var binding: ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance() //instanciamos la base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup
        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email = email ?: "", provider = provider ?: "")
    }

    private fun setup(email: String, provider: String) {

        title = "Gestionar citas "
        binding.emailTextView.text = email
        //binding.providerTextView.text = provider SE PUEDE QUITAR??

        binding.logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed() //vuelve a la activity anterior
        }


        binding.saveButton.setOnClickListener {
            db.collection("users").document(email).set(
                hashMapOf(
                    "provider" to provider,
                    "cita" to binding.adressTextView.text.toString(),
                    "hospital" to binding.phoneTextView.text.toString(),
                    "cita2" to binding.date2TextView.text.toString(),
                    "hospital2" to binding.hospital2TextView.text.toString(),
                    "cita3" to binding.date3TextView.text.toString(),
                    "hospital3" to binding.hospital3TextView.text.toString()
                )
            )

        }
        binding.getButton.setOnClickListener {
            db.collection("users").document(email).get().addOnSuccessListener {
                binding.adressTextView.setText(it.get("cita") as String?)
                binding.phoneTextView.setText(it.get("hospital") as String?)
                binding.date2TextView.setText(it.get("cita2") as String?)
                binding.hospital2TextView.setText(it.get("hospital2") as String?)
                binding.date3TextView.setText(it.get("cita3") as String?)
                binding.hospital3TextView.setText(it.get("hospital3") as String?)
            }

        }
        binding.deleteButton.setOnClickListener {
            db.collection("users").document(email).delete()
        }
    }
}