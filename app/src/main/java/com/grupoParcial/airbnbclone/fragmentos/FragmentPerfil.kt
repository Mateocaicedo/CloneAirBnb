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
import com.grupoParcial.airbnbclone.adaptadores.ReservasAdapter
import com.grupoParcial.airbnbclone.model.Reservas

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPerfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPerfil : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val AlojamientoUsuario :ArrayList<Reservas> by lazy { getValores() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ReservasAdapter
    private val LayoutManager by lazy { LinearLayoutManager(context) }


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
        val view=inflater.inflate(R.layout.fragment_perfil, container, false)
        recycler = view.findViewById(R.id.PerfilAlojamientoUser) as RecyclerView

        setRecyclerView()

        return view
    }

    fun setRecyclerView(){
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        adapter = (ReservasAdapter(AlojamientoUsuario))
        recycler.adapter = adapter
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
}