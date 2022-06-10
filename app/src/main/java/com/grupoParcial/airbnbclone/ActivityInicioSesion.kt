package com.grupoParcial.airbnbclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ActivityInicioSesion : AppCompatActivity() {
    private val activador = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciarsesion)

        val crearCuenta = findViewById<TextView>(R.id.crearCuenta)

        setupIniciar()

        crearCuenta.setOnClickListener {
            val intent = Intent(this, ActivityCrearCuenta::class.java)
            activador.launch(intent)
        }
    }

    private fun setupIniciar() {
        title = "Iniciar Sesion"
        val correo = findViewById<TextInputEditText>(R.id.iniciarCorreo)
        val contraseña = findViewById<TextInputEditText>(R.id.iniciarContrasena)
        val iniciarBoton = findViewById<Button>(R.id.iniciarBoton)

        iniciarBoton.setOnClickListener {
            if (correo.text.toString().isEmpty() || contraseña.text.toString().isEmpty()) {
                correo.error = "Campo obligatorio"
                contraseña.error = "Campo obligatorio"
                Toast.makeText(this, "Faltan campos ", Toast.LENGTH_SHORT).show()
            }
            else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(correo.text.toString(), contraseña.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            terminarRegistro(it.result.user?.email ?: "", ProviderType.BASIC)
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