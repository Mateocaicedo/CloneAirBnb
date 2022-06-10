package com.grupoParcial.airbnbclone.fragmentos

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.grupoParcial.airbnbclone.R
import com.google.firebase.auth.FirebaseAuth
import com.grupoParcial.airbnbclone.CrearAlojamiento
import com.grupoParcial.airbnbclone.MainActivity
import com.grupoParcial.airbnbclone.EditarPerfil

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSettings.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSettings : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_settings, container, false)

        val cerrarSesion = view?.findViewById<Button>(R.id.cerrarSesion)
        val editarPerfil = view?.findViewById<Button>(R.id.editarPerfil)
        val crearAlojamiento = view?.findViewById<Button>(R.id.crearAlojamiento)

        cerrarSesion?.setOnClickListener {
            TerminarSesion()
        }

        editarPerfil?.setOnClickListener {
            val intent = Intent(activity, EditarPerfil::class.java)
            startActivity(intent)
        }
        crearAlojamiento?.setOnClickListener {
            val intent = Intent(activity, CrearAlojamiento::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun TerminarSesion() {
        val prefs = this.activity?.getSharedPreferences((getString(R.string.prefs_file_name)), MODE_PRIVATE)?.edit()
        prefs?.clear()
        prefs?.apply()

        FirebaseAuth.getInstance().signOut()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentSettings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSettings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}