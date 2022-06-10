package com.grupoParcial.airbnbclone

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private val array = arrayListOf<kotlin.collections.MutableMap<String, String>>(mutableMapOf("TEST" to "TEST"),mutableMapOf("TEST" to "TEST"),mutableMapOf("TEST" to "TEST"),mutableMapOf("TEST" to "TEST"))
    val xsr = array[0].get("TEST")
    private val GOOGLE_SIGN_IN = 100
    private val clientID = "1088940096925-tetm4qtco3vnv6c38tm86cs524qbgmdn.apps.googleusercontent.com"
    private val activador = registerForActivityResult(StartActivityForResult()) {}

    val db = FirebaseFirestore.getInstance()

    val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val bundle = Bundle()
        auth = Firebase.auth

        bundle.putString("Mensaje", "Esta en el inicio")

        var iniciaGoogle = findViewById<Button>(R.id.iniciarGoogle)
        iniciaGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(clientID)
                .requestEmail()
                .build()
            val GoogleSignInClient = GoogleSignIn.getClient(this, gso)
            val signInIntent = GoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
            GoogleSignInClient.signOut()
        }

        val IniciarSesion = findViewById<Button>(R.id.iniciarsesion)
        IniciarSesion.setOnClickListener {
            val intent = Intent(this, ActivityInicioSesion::class.java)
            activador.launch(intent)
        }

        val crearCuenta = findViewById<TextView>(R.id.crearCuenta)
        crearCuenta.setOnClickListener {
            val intent = Intent(this, ActivityCrearCuenta::class.java)
            activador.launch(intent)
        }
        sesion()
    }

    private fun sesion() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file_name), MODE_PRIVATE)
        val provider = prefs.getString("provider", null)

        if (user != null) {
            val nombre = user.displayName; val email = user.email; val foto = user.photoUrl; val id = user.uid
            terminarRegistro(user.email ?: "", ProviderType.valueOf((provider?: ProviderType.BASIC) as String))
        } else {
            Toast.makeText(this, "No hay sesion iniciada", Toast.LENGTH_LONG).show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener(this) {
                            if (it.isSuccessful) {
                                terminarRegistro(account.email ?: "", ProviderType.GOOGLE)
                                db.collection("user").document(account.email ?: "").set(
                                    hashMapOf(
                                        "provider" to ProviderType.BASIC,
                                        "nombre" to account.displayName,
                                        "apellido" to "",
                                        "pais" to "",
                                        "tipo" to "0",
                                        "descripcion" to "",
                                        "foto" to account.photoUrl?.toString(),
                                    )
                                )
                            }
                        }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Error: No se pudo iniciar sesion", Toast.LENGTH_LONG).show()
            }
        }
    }
}