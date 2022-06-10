package com.grupoParcial.airbnbclone

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.grupoParcial.airbnbclone.adaptadores.fotosCrearAlojamiento
import com.hbb20.CountryCodePicker

class CrearAlojamiento : AppCompatActivity() {

    val user = FirebaseAuth.getInstance().currentUser

    lateinit var Uri: Uri
    var db = FirebaseFirestore.getInstance()
    private val pickImage = 100

    private val AlojamientoUsuario: ArrayList<Uri> = ArrayList()

    private val fotosFirebaseLink: ArrayList<String> = ArrayList()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: fotosCrearAlojamiento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_alojamiento)

        val botonCrearAlojamiento = findViewById<android.widget.Button>(R.id.botonAlojamientoCrear)
        val nombreAlojamiento = findViewById<android.widget.EditText>(R.id.nombreAlojamientoCrear)
        val direccionAlojamiento =
            findViewById<android.widget.EditText>(R.id.direccionAlojamientoCrear)
        val precioAlojamiento = findViewById<android.widget.EditText>(R.id.precioAlojamientoCrear)
        val paisAlojamiento = findViewById<CountryCodePicker>(R.id.paisAlojamientoCrear)
        val botonAgregarFoto = findViewById<android.widget.Button>(R.id.agregarFoto)

        recycler = findViewById<RecyclerView>(R.id.carruselFotos)

        botonAgregarFoto.setOnClickListener {
            checkPermission()
        }

        botonCrearAlojamiento.setOnClickListener {
            if (nombreAlojamiento.text.toString().isEmpty() || direccionAlojamiento.text.toString()
                    .isEmpty() || precioAlojamiento.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()

                if (nombreAlojamiento.text.toString().isEmpty()) {
                    nombreAlojamiento.error = "Falta llenar comapo"
                }
                if (direccionAlojamiento.text.toString().isEmpty()) {
                    direccionAlojamiento.error = "Falta llenar comapo"
                }
                if (precioAlojamiento.text.toString().isEmpty()) {
                    precioAlojamiento.error = "Falta llenar comapo"
                }

            } else {
                val docRef = db.collection("alojamientos").document()
                docRef.set(
                    mapOf(
                        "nombre" to nombreAlojamiento.text.toString(),
                        "direccion" to direccionAlojamiento.text.toString(),
                        "precio" to precioAlojamiento.text.toString(),
                        "usuario" to user?.email,
                        "fotos" to listOf<String>(),
                        "pais" to paisAlojamiento.selectedCountryNameCode
                    )
                )
                subirFoto(AlojamientoUsuario, docRef)
            }
        }
    }

    private fun openCamera() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) { //El permiso no está aceptado ni denegado.
            requestCameraPermission()
        } else {
            //El permiso está aceptado.
            openCamera()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                123
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == RESULT_OK && data != null) {
            Uri = (data.getData() ?: null) as Uri

            AlojamientoUsuario.add(Uri)

            val foto = findViewById<ShapeableImageView>(R.id.fotoEditar)
            setRecyclerView()
        }
    }

    fun subirFoto(ListaDeUris: ArrayList<Uri>, docRef: DocumentReference) {
        val storage = Firebase.storage
        var storageRef = storage.reference

        var contador = 0
        val estadoFoto = findViewById<TextView>(R.id.EstadoAlojamiento)

        for (i in 0 until ListaDeUris.size) {
            val riversRef = storageRef.child("alojamientos/" + (docRef.id) + "/" + i)
            val uploadTask = riversRef.putFile(ListaDeUris[i])
            uploadTask.addOnFailureListener {
            }.addOnSuccessListener {
                contador = i
                estadoFoto.text = (contador == ListaDeUris.size - 1).toString()
                if (contador == ListaDeUris.size - 1) {
                    Toast.makeText(this, "Alojamiento creado", Toast.LENGTH_LONG).show()
                    guardarFotos(ListaDeUris, docRef)
                }
            } .addOnProgressListener { taskSnapshot ->
                val progress = (100 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount)
                estadoFoto.text = "Subiendo foto ${i+1}: $progress%"
            } .addOnFailureListener {
                Toast.makeText(this, "Error al subir foto", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun guardarFotos(ListaDeUris: ArrayList<Uri>, docRef: DocumentReference) {
        val storage = Firebase.storage
        var storageRef = storage.reference
        var db = FirebaseFirestore.getInstance()

        for (i in 0 until ListaDeUris.size) {

            var link = "alojamientos/${docRef.id.toString()}/$i"
            println(link)

            val riversRef = storageRef.child(link)
            val urlTask = riversRef.downloadUrl
            urlTask.addOnSuccessListener {
                fotosFirebaseLink.add(it.toString())
                docRef.update("fotos", fotosFirebaseLink)
            } .addOnFailureListener {
                Toast.makeText(this, "Error al guardar fotos", Toast.LENGTH_LONG).show()
            }
            db.collection("user").document(docRef.id).update("foto", fotosFirebaseLink).addOnSuccessListener {
                db.collection("user").document(docRef.id).update("tipo", "1").addOnFailureListener() {
                    Toast.makeText(this, "Error al guardar fotos", Toast.LENGTH_LONG).show()
                }
            }


            finish()
        }
    }

    fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = (fotosCrearAlojamiento(AlojamientoUsuario))
        recycler.adapter = adapter
    }

}