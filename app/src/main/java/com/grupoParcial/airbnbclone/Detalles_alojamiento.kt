package com.grupoParcial.airbnbclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.hbb20.CountryCodePicker
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class Detalles_alojamiento : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alojamiento)

        val bundle = intent.extras
        val nombre = bundle?.getString("nombre")
        val direccion = bundle?.getString("direccion")
        val precio = bundle?.getString("precio")
        val des = bundle?.getString("descripcion")
        val pais = bundle?.getString("pais")
        val array = bundle?.getStringArrayList("array")

        fun getFotos(): ArrayList<CarouselItem> {
            return object : ArrayList<CarouselItem>() {
                init {
                    for (index in array!!.indices) {
                        add(CarouselItem("${array[index]}"))
                    }

                }
            }
        }

        val nombres = findViewById<TextView>(R.id.nombredetails)
        val direccions = findViewById<TextView>(R.id.direcciondetaiils)
        val precios = findViewById<TextView>(R.id.preciodetails)
        val descripcions = findViewById<TextView>(R.id.descipciondetails)
        val paisDetail = findViewById<TextView>(R.id.Pais)
        val imagen = findViewById<ImageCarousel>(R.id.carouseldetails)
        val invisiblePais = findViewById<CountryCodePicker>(R.id.invisiblePaisAlojamiento)
        imagen.addData(getFotos())
        nombres.text = nombre
        direccions.text = direccion
        precios.text = precio
        descripcions.text = des

        invisiblePais?.setCountryForNameCode(pais)
        paisDetail.text = invisiblePais?.selectedCountryName


    }
}