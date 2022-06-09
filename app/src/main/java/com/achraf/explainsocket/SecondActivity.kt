package com.achraf.explainsocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.achraf.explainsocket.databinding.ActivitySecondBinding
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.lang.Exception

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var mSocket: Socket
    private val gson: Gson = Gson()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMyUsername.text = Constants.myUsername
        binding.tvMyId.text = "My Id: ${Constants.myId}"

        binding.rvChat.layoutManager = LinearLayoutManager(this)

        binding.btnSend.setOnClickListener {

            if (binding.etToId.text.isNullOrEmpty() || binding.etMessage.text.isNullOrEmpty()) {

                Toast.makeText(this, "Please Fill All The Outputs!", Toast.LENGTH_SHORT).show()

            } else {

                val message = Message(binding.etToId.text.toString().toInt(), Constants.myUsername, binding.etMessage.text.toString())
                sendMessage(message)

            }

        }


        try {
            mSocket = IO.socket("https://445d-105-155-75-85.ngrok.io")

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("test101", "Failed to connect")
        }

        mSocket.connect()
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on("getMessage", onGetMessage)




    }


    private var onConnect = Emitter.Listener {
        val jsonData = gson.toJson(Constants.myId)
        mSocket.emit("newUser", jsonData)

    }

    private var onGetMessage = Emitter.Listener {
        val request: Message = gson.fromJson(it[0].toString(), Message::class.java)
        Log.d("test101", "request = $request")
        val toId = request.toId
        val username = request.myUsername
        val message = request.message
        showMessage(Message(toId, username, message))
    }

    private fun showMessage(message: Message) {
        runOnUiThread {
            adapter.setMessage(message)
            binding.rvChat.adapter = adapter
        }
    }



    private fun sendMessage(message: Message) {
        val jsonData = gson.toJson(message)
        mSocket.emit("sendMessage", jsonData)
        showMessage(message)
    }





}