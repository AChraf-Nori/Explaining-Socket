package com.achraf.explainsocket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.achraf.explainsocket.databinding.ActivityMainBinding
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnNext.setOnClickListener {

            if (binding.etId.text.isNullOrEmpty() || binding.etUsername.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please Fill All The Outputs!", Toast.LENGTH_SHORT).show()

            } else {
                Constants.myId = binding.etId.text.toString().toInt()
                Constants.myUsername = binding.etUsername.text.toString()

                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                finish()
            }

        }


    }


}