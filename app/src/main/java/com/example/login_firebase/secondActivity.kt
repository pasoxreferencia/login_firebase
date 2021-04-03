package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivitySecondBinding

private lateinit var binding: ActivitySecondBinding
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}