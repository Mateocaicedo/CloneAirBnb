package com.grupoParcial.airbnbclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hbb20.CountryCodePicker


class ActivityCrearCuenta : AppCompatActivity() {
    private val activador = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    }
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crearcuenta)
        setupRegistro()

    }

    private fun setupRegistro() {
        title = "Registro"

        val correo = findViewById<TextInputEditText>(R.id.CorreoRegistro)
        val contraseñatxt = findViewById<TextInputLayout>(R.id.ContraseñaRegistroTxt)
        val contraseña = findViewById<TextInputEditText>(R.id.ContraseñaRegistro)
        val confirmar = findViewById<TextInputEditText>(R.id.ConfirmarRegistro)
        val confirmartxt = findViewById<TextInputLayout>(R.id.ConfirmarRegistroTxt)
        val nombre = findViewById<TextInputEditText>(R.id.NombreRegistro)
        val apellido = findViewById<TextInputEditText>(R.id.ApellidoRegistro)
        val RegistrarButton = findViewById<Button>(R.id.registrarButton)


        RegistrarButton.setOnClickListener {
            if (correo.text.toString().isEmpty() || contraseña.text.toString().isEmpty() || confirmar.text.toString().isEmpty() || nombre.text.toString().isEmpty() || apellido.text.toString().isEmpty()) {

                if (correo.text.toString().isEmpty()) { correo.error = "Campo obligatorio" }
                if (contraseña.text.toString().isEmpty()) { contraseñatxt.error = "Campo obligatorio" }
                if (confirmar.text.toString().isEmpty()) { confirmartxt.error = "Campo obligatorio" }
                if (nombre.text.toString().isEmpty()) { nombre.error = "Campo obligatorio" }
                if (apellido.text.toString().isEmpty()) { apellido.error = "Campo obligatorio" }
            }
            else if (contraseña.text.toString() != confirmar.text.toString()) {
                contraseñatxt.error = "Contraseñas no coinciden"
                confirmartxt.error = "Contraseñas no coinciden"
            }
            else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.text.toString(), contraseña.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val correo = it.result?.user?.email
                            terminarRegistro(correo ?: "", ProviderType.BASIC)
                            db.collection("user").document(correo.toString()).set(
                                hashMapOf(
                                    "provider" to ProviderType.BASIC,
                                    "nombre" to nombre.text.toString(),
                                    "apellido" to apellido.text.toString(),
                                    "pais" to "",
                                    "tipo" to "0",
                                    "descripcion" to "",
                                    "foto" to R.string.default_profile_pic.toString(),
                                )
                            )
                        } else {
                            Toast.makeText(this, "Error al registrarse", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    private fun terminarRegistro(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, MainActivity2::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
        finish()
    }
}