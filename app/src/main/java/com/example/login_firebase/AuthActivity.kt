package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.login_firebase.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: ActivityAuthBinding
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup
        setup()
    }

    private fun setup() {
        title = "Autenticación"
        binding.signUpButton.setOnClickListener {
            if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString()).addOnCompleteListener {

                            if (it.isSuccessful) {
                                showHome(it.result?.user?.email?: "", ProviderType.BASIC)
                            } else {
                                showAlert()
                            }

                        }


                }
            else {
                showAlert2()
            }

        }

        binding.loginButton.setOnClickListener {
            if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString()).addOnCompleteListener {

                            if (it.isSuccessful) {
                                showHome(it.result?.user?.email?: "", ProviderType.BASIC)
                            } else {
                                showAlert()
                            }

                        }

            }

            else {
                showAlert2()
            }

        }
    }

    private fun showAlert() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert2() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se han introducido datos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome (email: String, provider: ProviderType){

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}
