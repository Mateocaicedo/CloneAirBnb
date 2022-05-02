package com.grupoParcial.airbnbclone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class ActivityCrearCuenta : AppCompatActivity() {
    private val activador = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crearcuenta)

        val BottonInicio = findViewById<Button>(R.id.iniciarHome)

        BottonInicio.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            activador.launch(intent)
        }

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        val fecha = findViewById<EditText>(R.id.FechaRegistro)
        fecha.setShowSoftInputOnFocus(false);

        fecha.setOnFocusChangeListener { view, b -> if (b== true){ datePicker.show(getSupportFragmentManager(),datePicker.toString())} }
        fecha.setOnClickListener { datePicker.show(getSupportFragmentManager(),datePicker.toString())}

        datePicker.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
            val date = dateFormatter.format(Date(it))
            fecha.setText(date)
        }
    }
}