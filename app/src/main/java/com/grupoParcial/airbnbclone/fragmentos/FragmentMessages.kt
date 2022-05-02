package com.grupoParcial.airbnbclone.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.R
import com.grupoParcial.airbnbclone.adaptadores.AlojamientosAdapter
import com.grupoParcial.airbnbclone.adaptadores.ChatAdapter
import com.grupoParcial.airbnbclone.model.Alojamientos
import com.grupoParcial.airbnbclone.model.Chat_loby

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [messages.newInstance] factory method to
 * create an instance of this fragment.
 */
class messages : Fragment(), ChatAdapter.OnItemCLick{
    private val list:ArrayList<Chat_loby> by lazy { getValores() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ChatAdapter
    private val LayoutManager by lazy { LinearLayoutManager(context) }

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private fun getValores(): ArrayList<Chat_loby>{
        return object: ArrayList<Chat_loby>(){
            init {
                add(Chat_loby("Mateo caicedo",
                    "Buenas quiero alquilar esta casa",
                    "17:20",
                    "https://htmlcolorcodes.com/assets/images/html-color-codes-color-palette-generators-thumb.jpg"))
            }
            init {
                add(Chat_loby("Juana Pi;eros",
                    "Make this",
                    "17:21",
                    "https://www.lavanguardia.com/files/article_main_microformat/uploads/2022/03/12/622c874213775.jpeg"))
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootview =inflater.inflate(R.layout.fragment_messages, container, false)
        recycler = rootview.findViewById(R.id.Mensaje_lista) as RecyclerView
        setRecyclerView()
        return rootview
    }

    private fun setRecyclerView(){
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = LayoutManager
        adapter = (ChatAdapter(list,this))
        recycler.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment messages.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            messages().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(position: Int, nombre: String, mensaje: String, hora: String) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
    }
}