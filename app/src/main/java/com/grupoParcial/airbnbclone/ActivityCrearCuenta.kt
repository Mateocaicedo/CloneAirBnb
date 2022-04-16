package com.grupoParcial.airbnbclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class ActivityCrearCuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crearcuenta)

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