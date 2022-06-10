package com.grupoParcial.airbnbclone

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.ThumbnailUtils.extractThumbnail
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.rpc.Help
import com.hbb20.CountryCodePicker
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.callbackFlow
import java.io.ByteArrayOutputStream


class EditarPerfil : AppCompatActivity() {

    val user = Firebase.auth.currentUser
    val db = FirebaseFirestore.getInstance()
    val storage = Firebase.storage

    lateinit var fotoVieja: String
    lateinit var fotoNueva: String

    lateinit var Uri : Uri
    lateinit var bitmap : Bitmap

    private val pickImage = 100

    var storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        CargarDatos()

        val cambiarFoto = findViewById<Button>(R.id.cambiarFotoEditar)

        cambiarFoto.setOnClickListener { checkPermission() }
    }

    private fun CargarDatos() {
        val nombre = findViewById<TextInputEditText>(R.id.nombreEditar)
        val apellido = findViewById<TextInputEditText>(R.id.apellidoEditar)
        val descrpcion = findViewById<TextInputEditText>(R.id.descripcionEditar)
        val pais = findViewById<CountryCodePicker>(R.id.paisEditar)
        val foto = findViewById<ShapeableImageView>(R.id.fotoEditar)

        db.collection("user").document(user?.email?:"").get().addOnSuccessListener { document ->
            nombre.setText(document.getString("nombre"))
            apellido.setText(document.getString("apellido"))
            descrpcion.setText(document.getString("descripcion"))
            if (document.getString("pais") != "") {
                pais.setCountryForNameCode(document.getString("pais"))
            }

            fotoVieja = document.get("foto").toString()

            Picasso.get().load(fotoVieja).fit().centerCrop().into(foto)
        }

        val botonEditar = findViewById<Button>(R.id.botonEditar)

        botonEditar.setOnClickListener {


            db.collection("user").document(user?.email?:"").update("nombre", nombre.text.toString())
            db.collection("user").document(user?.email?:"").update("apellido", apellido.text.toString())
            db.collection("user").document(user?.email?:"").update("descripcion", descrpcion.text.toString())
            db.collection("user").document(user?.email?:"").update("pais", pais.selectedCountryNameCode)

            subirFoto(Uri)

            storageRef.child("fotoPerfil/${user?.email?:""}").downloadUrl.addOnSuccessListener { Url ->
                db.collection("user").document(user?.email?:"").update("foto", Url.toString())

        }.addOnFailureListener {
                // Handle any errors
            }

            finish()
        }
    }

    private fun openCamera() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) { //El permiso no está aceptado ni denegado.
            requestCameraPermission()
        } else {
            //El permiso está aceptado.
            openCamera()
        }
    }
    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
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
            Uri = (data.getData()?:null) as Uri
            val foto = findViewById<ShapeableImageView>(R.id.fotoEditar)
            resizeImage()
            Picasso.get().load(Uri).fit().centerCrop().into(foto)
        }
    }

    fun subirFoto(uri: Uri) {
        val riversRef = storageRef.child("fotoPerfil/" + (user?.email?.format() ?: ""))

        val uploadTask = riversRef.putFile(uri)

        uploadTask?.addOnFailureListener {
            // Handle unsuccessful uploads
        }?.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
    fun resizeImage() {
        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri)
        bitmap = extractThumbnail(bitmap, 250, 250)
        getImageUri(this, bitmap)
    }

    fun getImageUri(inContext: Context, inImage: Bitmap){
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.getContentResolver(),
            inImage,
            "Title",
            null
        )
        Uri = android.net.Uri.parse(path)
    }
}