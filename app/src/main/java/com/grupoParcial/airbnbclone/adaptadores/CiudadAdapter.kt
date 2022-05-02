package com.grupoParcial.airbnbclone.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.databinding.CardGrandeBinding
import com.grupoParcial.airbnbclone.databinding.CardReservasguardadasBinding
import com.grupoParcial.airbnbclone.model.Ciudad
import com.grupoParcial.airbnbclone.model.Reservas
import com.squareup.picasso.Picasso

class CiudadAdapter(val reservas:List<Ciudad>):RecyclerView.Adapter<CiudadAdapter.ViewHolder>() {

    interface  OnItemCLick{
        fun onItemClick(position: Int, ciudad: String, DistanciaCard:String, PaisCard:String, fotos:String)
    }

    class ViewHolder(val binding: CardGrandeBinding): RecyclerView.ViewHolder(binding.root){

        fun render(rese: Ciudad){
            binding.CiudadCard.text = rese.ciudad
            binding.DistanciaCard.text = rese.distancia
            binding.PaisCard.text = rese.pais
            Picasso.get().load(rese.imagen).into(binding.imagensearch)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CardGrandeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(reservas[position])
    }

    override fun getItemCount(): Int =reservas.size
}