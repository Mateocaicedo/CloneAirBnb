package com.grupoParcial.airbnbclone.adaptadores

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.databinding.CarruselFotoBinding
import com.squareup.picasso.Picasso

class fotosCrearAlojamiento(val Uris:List<Uri>):RecyclerView.Adapter<fotosCrearAlojamiento.ViewHolder>() {

    class ViewHolder(val binding: CarruselFotoBinding): RecyclerView.ViewHolder(binding.root){

        fun render(fotoUri: Uri){
            Picasso.get().load(fotoUri).fit().centerCrop().into(binding.fotoAlojamiento)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CarruselFotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(Uris[position])
    }

    override fun getItemCount(): Int =Uris.size
}