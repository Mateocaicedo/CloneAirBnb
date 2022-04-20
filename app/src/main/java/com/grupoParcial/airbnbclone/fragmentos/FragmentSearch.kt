package com.grupoParcial.airbnbclone.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.R
import com.grupoParcial.airbnbclone.model.Alojamientos
import com.grupoParcial.airbnbclone.adaptadores.AlojamientosAdapter
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
class search : Fragment() {

    private val list:ArrayList<Alojamientos> by lazy { getValores() }
    private lateinit var recycler:RecyclerView
    private lateinit var adapter: AlojamientosAdapter
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

    private fun getFotos(): ArrayList<CarouselItem>{
        return object: ArrayList<CarouselItem>(){
            init {
                add(CarouselItem("https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"))
                add(CarouselItem("https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"))
                add(CarouselItem("https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"))
                add(CarouselItem("https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"))
                add(CarouselItem("https://i.blogs.es/05285b/cuerpo-20sin-20grasa/450_1000.jpg"))

            }
        }
    }
    private fun getValores(): ArrayList<Alojamientos>{
        return object: ArrayList<Alojamientos>(){
            init {
                add(
                    Alojamientos("Rincon de georgio",
                        "Pura marica que viene aqui",
                        "Sal si puedes",
                        150,
                        getFotos())
                )
                add(
                    Alojamientos("Rincon de georgio",
                        "Pura marica que viene aqui",
                        "Sal si puedes",
                        150,
                        getFotos())
                )
                add(
                    Alojamientos("Rincon de georgio",
                        "Pura marica que viene aqui",
                        "Sal si puedes",
                        150,
                        getFotos())
                )



            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootview =inflater.inflate(R.layout.fragment_search, container, false)
        recycler = rootview.findViewById(R.id.ListaResultados) as RecyclerView
        setRecyclerView()
        return rootview
    }

    private fun setRecyclerView(){
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = LayoutManager
        adapter = (AlojamientosAdapter(list))
        recycler.adapter = adapter
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
}