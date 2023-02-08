package com.example.myfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById<View>(R.id.nameText) as EditText
        editTextPhone = findViewById<View>(R.id.phoneText) as EditText
        file = File(this.filesDir, FILE_NAME)
    }

    companion object {
        const val FILE_NAME = "contacts.txt"
    }
    private var editTextName: EditText? = null
    private var editTextPhone: EditText? = null
    private var file: File? = null
    private var outputStream: FileOutputStream? = null
    private var inputStream: FileInputStream? = null

    fun save(v: View?) {
        val data = editTextName!!.text.toString() + "|" + editTextPhone!!.text.toString()
        try {
            outputStream = FileOutputStream(file)
            outputStream!!.write(data.toByteArray())
            outputStream!!.close()
            Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun load(v: View?) {
        val length = file!!.length().toInt()
        val bytes = ByteArray(length)
        try {
            inputStream = FileInputStream(file)
            inputStream!!.read(bytes)
            inputStream!!.close()
            val data = String(bytes)
            editTextName!!.setText(data.split("|").toTypedArray()[0])
            editTextPhone!!.setText(data.split("|").toTypedArray()[1])
            Toast.makeText(baseContext, "data loaded", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    }
