package com.grupoParcial.airbnbclone.fragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.grupoParcial.airbnbclone.Detalles_alojamiento
import com.grupoParcial.airbnbclone.R
import com.grupoParcial.airbnbclone.adaptadores.AlojamientosAdapter
import com.grupoParcial.airbnbclone.model.Alojamientos
import com.hbb20.CountryCodePicker
import com.squareup.picasso.Picasso
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPerfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPerfil : Fragment(), AlojamientosAdapter.OnItemCLick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var list: ArrayList<Alojamientos>

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: AlojamientosAdapter

    val user = Firebase.auth.currentUser
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun getFotos(fotosEncontradas: ArrayList<String>): ArrayList<CarouselItem> {
        val fotos = ArrayList<CarouselItem>()

        for (foto in fotosEncontradas) {
            fotos.add(CarouselItem(foto))
        }
        return fotos
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        setPerfil()

        recycler = view.findViewById(R.id.PerfilAlojamientoUser) as RecyclerView

        setRecyclerView()
        return view
    }

    private fun setPerfil() {
        db.collection("user").document(user?.email ?: "").get().addOnSuccessListener { document ->
            if (document != null) {
                val foto = view?.findViewById<ShapeableImageView>(R.id.Foto)
                val nombre = view?.findViewById<TextView>(R.id.PerfilNombre)
                val pais = view?.findViewById<TextView>(R.id.PerfilUbicacion)
                val descripcion = view?.findViewById<TextView>(R.id.PerfilDescripcion)
                val tipo = view?.findViewById<TextView>(R.id.PerfilTipo)
                val invisiblePais = view?.findViewById<CountryCodePicker>(R.id.invisiblePais)

                val urlFoto = document.get("foto").toString()

                try {
                    Picasso.get().load(urlFoto).fit().centerCrop().into(foto)
                } catch (e: Exception) {
                }
                nombre?.text = "${document.getString("nombre")} ${document.getString("apellido")}"


                descripcion?.text = if (document.get("descripcion") != "") {
                    document.getString("descripcion")
                } else {
                    "Aun no tiene descripcion"
                }

                pais?.text = if (document.getString("pais") != "") {
                    invisiblePais?.setCountryForNameCode(document.getString("pais"))
                    invisiblePais?.selectedCountryName
                } else {
                    "Pais no definido"
                }
                tipo?.text = if (document.getString("tipo").toString() == "0") {
                    "Huesped"
                } else {
                    "Anfitrion - Huesped"
                }

            }
        }

    }

    private fun setRecyclerView() {
        val alojamientos = ArrayList<Alojamientos>()
        db.collection("alojamientos").whereEqualTo("usuario", user?.email).get()
            .addOnSuccessListener { document ->
                document.forEach {
                    alojamientos.add(
                        Alojamientos(
                            it.data["nombre"].toString(),
                            "",
                            it.data["direccion"].toString(),
                            it.data["precio"].toString(),
                            it.data["pais"].toString(),
                            getFotos(it.data["fotos"] as ArrayList<String>) as ArrayList<CarouselItem>
                        )
                    )
                }
                list = alojamientos

                recycler.setHasFixedSize(true)
                recycler.itemAnimator = DefaultItemAnimator()
                recycler.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                Toast.makeText(context, "${list.size}", Toast.LENGTH_LONG).show()

                adapter = (AlojamientosAdapter(list, this))
                recycler.adapter = adapter
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentPerfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPerfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(
        position: Int,
        nombre: String,
        precio: String,
        desc: String,
        dire: String,
        pais: String,
        imagen: ArrayList<String?>
    ) {
        Toast.makeText(context, "Item $position", Toast.LENGTH_SHORT).show()
        val intento1 = Intent(context, Detalles_alojamiento::class.java)
        intento1.putExtra("nombre", nombre)
        intento1.putExtra("direccion", dire)
        intento1.putExtra("precio", precio)
        intento1.putExtra("descripcion", desc)
        intento1.putExtra("pais", pais)
        intento1.putStringArrayListExtra("array", imagen)

        startActivity(intento1)
    }

}