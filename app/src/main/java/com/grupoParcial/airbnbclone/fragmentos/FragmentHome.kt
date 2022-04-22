package com.grupoParcial.airbnbclone.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.R
import com.grupoParcial.airbnbclone.adaptadores.ReservasAdapter
import com.grupoParcial.airbnbclone.model.Alojamientos
import com.grupoParcial.airbnbclone.model.Reservas
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : Fragment() {

    private val list:ArrayList<Reservas> by lazy { getValores() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ReservasAdapter
    private val LayoutManager by lazy { LinearLayoutManager(context) }

    private val list2:ArrayList<Reservas> by lazy { getValores() }
    private lateinit var recycler2: RecyclerView
    //private lateinit var adapter2: ReservasAnterioresAdapter
    private val LayoutManager2 by lazy { LinearLayoutManager(context) }
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

    private fun getValores(): ArrayList<Reservas>{
        return object: ArrayList<Reservas>(){
            init {
                add(Reservas("Nombre","Descripcion", "https://cdn.plainconcepts.com/wp-content/uploads/2021/12/Web3.0.jpg"))
                add(Reservas("Nombre","Descripcion", "https://cdn.plainconcepts.com/wp-content/uploads/2021/12/Web3.0.jpg"))
                add(Reservas("Nombre","Descripcion", "https://cdn.plainconcepts.com/wp-content/uploads/2021/12/Web3.0.jpg"))
                add(Reservas("Nombre","Descripcion", "https://cdn.plainconcepts.com/wp-content/uploads/2021/12/Web3.0.jpg"))
                add(Reservas("Nombre","Descripcion", "https://cdn.plainconcepts.com/wp-content/uploads/2021/12/Web3.0.jpg"))

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view=inflater.inflate(R.layout.fragment_home, container, false)
        recycler = view.findViewById(R.id.reservasG) as RecyclerView
        recycler2 = view.findViewById(R.id.reservasAnteriores) as RecyclerView

        setRecyclerView()
        setRecyclerView2()
        val image = view.findViewById(R.id.imagenAloja) as ImageView
        Picasso.get()
            .load("https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/1200/public/media/image/2022/01/web3-2585263.jpg?itok=zHqw-e1i")
            .error(R.mipmap.ic_launcher_round)
            .into(image)
        return view

    }

    fun setRecyclerView(){
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        adapter = (ReservasAdapter(list))
        recycler.adapter = adapter
    }

    fun setRecyclerView2(){
        recycler2.setHasFixedSize(true)
        recycler2.itemAnimator = DefaultItemAnimator()
        recycler2.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        adapter = (ReservasAdapter(list2))
        recycler2.adapter = adapter
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}