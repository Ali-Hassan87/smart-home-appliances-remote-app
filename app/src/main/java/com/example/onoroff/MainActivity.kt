package com.example.onoroff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectButton = findViewById<Button>(R.id.btnConnect)
        val ipField = findViewById<EditText>(R.id.editIp)
        val portField = findViewById<EditText>(R.id.editPort)
        val statusText = findViewById<TextView>(R.id.textViewStatus)

        connectButton.setOnClickListener {
            val ip = ipField.text.toString()
            val port = portField.text.toString().toIntOrNull() ?: 0

            if (ip.isBlank() || port == 0) {
                Toast.makeText(this, "Enter valid IP and Port", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Open chat activity with connection details
            val intent = Intent(this, CommandActivity::class.java)
            intent.putExtra("IP", ip)
            intent.putExtra("PORT", port)
            startActivity(intent)
        }
    }
}