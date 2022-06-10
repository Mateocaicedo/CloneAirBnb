package com.grupoParcial.airbnbclone.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.common.math.PairedStats
import com.grupoParcial.airbnbclone.Detalles_alojamiento
import com.grupoParcial.airbnbclone.R
import com.grupoParcial.airbnbclone.adaptadores.AlojamientosAdapter
import com.grupoParcial.airbnbclone.adaptadores.CiudadAdapter
import com.grupoParcial.airbnbclone.model.Alojamientos
import com.grupoParcial.airbnbclone.model.Ciudad
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [search.newInstance] factory method to
 * create an instance of this fragment.
 */
class search : Fragment(), AlojamientosAdapter.OnItemCLick {
    private val activador = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    }
    private val list: ArrayList<Alojamientos> by lazy { getValores() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: AlojamientosAdapter


    private val list2: ArrayList<Ciudad> by lazy { getValores2() }
    private lateinit var recycler2: RecyclerView
    private lateinit var adapter2: CiudadAdapter

    private val LayoutManager by lazy { LinearLayoutManager(context) }


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun getValores2(): ArrayList<Ciudad> {
        return object : ArrayList<Ciudad>() {
            init {
                add(
                    Ciudad(
                        "Bogota",
                        "Colombia",
                        "1000.4 Km",
                        "https://content.r9cdn.net/rimg/dimg/64/c2/60f6a60a-city-52059-177d8176717.jpg?crop=true&width=1020&height=498"
                    )
                )
                add(
                    Ciudad(
                        "Cartagena",
                        "Colombia",
                        "200.4 Km",
                        "https://cdn.colombia.com/images/v2/turismo/sitios-turisticos/cartagena/ciudad-cartagena-800.jpg"
                    )
                )
                add(
                    Ciudad(
                        "Barranquilla",
                        "Colombia",
                        "100.8 Km",
                        "https://cdn.pixabay.com/photo/2015/03/27/14/34/churches-694771_960_720.jpg"
                    )
                )
            }
        }
    }


    private fun getValores(): ArrayList<Alojamientos> {
        return object : ArrayList<Alojamientos>() {
            init {
                add(
                    Alojamientos(
                        "Rincon del vago",
                        "2 Habitaciones - 3 camas",
                        "cr 88 #81-188",
                        "150",
                        "hi",
                        getFotos()
                    )
                )
                add(
                    Alojamientos(
                        "Alcatraz",
                        "2 Habitaciones - 3 camas",
                        "cr 88 #81-188",
                        "150",
                        "hi",
                        getFotos2()
                    )
                )
                add(
                    Alojamientos(
                        "Mirabella",
                        "2 Habitaciones - 3 camas",
                        "cr 88 #81-188",
                        "150",
                        "hi",
                        getFotos()
                    )
                )
            }
        }
    }
    private fun getFotos(): ArrayList<CarouselItem> {
        return object : ArrayList<CarouselItem>() {
            init {
                add(CarouselItem("https://images.alphacoders.com/112/1122706.png"))
                add(CarouselItem("https://preview.redd.it/vsifwh3t5ww61.png?auto=webp&s=bf5cbedfca188048ccf8a878146f55eaeccfb776"))
                add(CarouselItem("https://staticdelivery.nexusmods.com/images/3333/11400410-1611689055.jpg"))

            }
        }
    }

    private fun getFotos2(): ArrayList<CarouselItem> {
        return object : ArrayList<CarouselItem>() {
            init {
                add(CarouselItem("https://i.pinimg.com/originals/55/c9/7c/55c97c51092d9bec00460abab958b5b9.jpg"))
                add(CarouselItem("https://www.gtabase.com/igallery/1101-1200/GTAOnline_Apartment_StiltHouse_01_Overview-1193-512.jpg"))
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootview = inflater.inflate(R.layout.fragment_search, container, false)
        recycler = rootview.findViewById(R.id.ListaResultados) as RecyclerView
        recycler2 = rootview.findViewById(R.id.ListaResultadoInicio) as RecyclerView
        setRecyclerView()
        setRecyclerView2()
        return rootview

        val barraBusqueda = rootview.findViewById<SearchView>(R.id.barraBusqueda)
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = LayoutManager
        adapter = (AlojamientosAdapter(list, this))
        recycler.adapter = adapter
    }

    private fun setRecyclerView2() {
        recycler2.setHasFixedSize(true)
        recycler2.itemAnimator = DefaultItemAnimator()
        recycler2.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        adapter2 = (CiudadAdapter(list2))
        recycler2.adapter = adapter2
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment search.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            search().apply {
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
        intento1.putStringArrayListExtra("array", imagen)

        startActivity(intento1)
    }


}