package com.grupoParcial.airbnbclone.model


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.R
import com.squareup.picasso.Picasso

class AlojamientosAdapter(val alojamientos:List<Alojamientos>):RecyclerView.Adapter<AlojamientosAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var descripcionvista: TextView 
        var nombrevista: TextView 
        var imagen: ImageView 
        var direccionvista: TextView 
        var precio: TextView 
        init {
            descripcionvista= itemView.findViewById(R.id.descripcion)
            nombrevista = itemView.findViewById(R.id.nombre)
            imagen= itemView.findViewById(R.id.imagen)
            direccionvista= itemView.findViewById(R.id.direccion)
            precio= itemView.findViewById(R.id.precio)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.resultaods_search, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newList = alojamientos[position]
        holder.nombrevista.text = newList.Nombre
        holder.descripcionvista.text = newList.Descripcion
        holder.precio.text = newList.Precio.toString()
        holder.direccionvista.text = newList.Direccion
        Picasso.get().load(newList.Imagen).into(holder.imagen)
    }

    override fun getItemCount(): Int =alojamientos.size
}