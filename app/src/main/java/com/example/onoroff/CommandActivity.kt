package com.example.onoroff

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class CommandActivity : AppCompatActivity() {

    private lateinit var socket: Socket
    private lateinit var writer: PrintWriter
    private lateinit var reader: BufferedReader


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command)

        val ip = intent.getStringExtra("IP") ?: ""
        val port = intent.getIntExtra("PORT", 34)
        val InputField = findViewById<EditText>(R.id.editinput)
        val Button1 = findViewById<Button>(R.id.btn1)
        val checkA1 = findViewById<CheckBox>(R.id.checkA1)
        val checkA0 = findViewById<CheckBox>(R.id.checkA0)

        // Start connection in new thread
        Thread {
            try {
                socket = Socket(ip, port)
                writer = PrintWriter(socket.getOutputStream())
                reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                // Update UI - Connection successful
                runOnUiThread {
                    findViewById<TextView>(R.id.textConnectionStatus).apply {
                        text = "Connected to $ip:$port"
                        setBackgroundColor(Color.GREEN)
                    }
                }

                // Start message receiver
                Thread {
                    try {
                        while (!socket.isClosed) {
                            val message = reader.readLine() ?: break  // Will be null if disconnected
                            runOnUiThread {
                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Server disconnected", Toast.LENGTH_LONG).show()
                            finish()  // Close chat screen
                        }
                    }
                }.start()

            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Connection failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }.start()

        // Send button click
        Button1.setOnClickListener {
            if (checkA1.isChecked) {
                Thread {
                    try {
                        val message = InputField.setText("A1")
                        writer.println(message)
                        writer.flush()
                        runOnUiThread {
                            Toast.makeText(this, "Send: A1", Toast.LENGTH_SHORT).show()
                            InputField.text.clear()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Send failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.start()
            }else if (checkA0.isChecked) {
            Thread {
                try {
                    val message = InputField.setText("A0")
                    writer.println(message)
                    writer.flush()
                    runOnUiThread {
                        Toast.makeText(this, "Send: A0", Toast.LENGTH_SHORT).show()
                        InputField.text.clear()
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "Send failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }.start()
        }else{
            Toast.makeText(this, "Select one Option!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Add the onBackPressed method here
    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Disconnecting...", Toast.LENGTH_SHORT).show()
        try {
            writer.close()
            reader.close()
            socket.close()
        } catch (e: Exception) {
            // Ignore
        }
        finish()  // Close the activity
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            writer.close()
            reader.close()
            socket.close()
        } catch (e: Exception) {
            // Ignore
        }
    }
}