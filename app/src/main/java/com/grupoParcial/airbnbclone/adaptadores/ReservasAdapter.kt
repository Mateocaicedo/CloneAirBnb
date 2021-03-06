package com.grupoParcial.airbnbclone.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.databinding.CardReservasguardadasBinding
import com.grupoParcial.airbnbclone.model.Reservas
import com.squareup.picasso.Picasso

class ReservasAdapter(val reservas:List<Reservas>):RecyclerView.Adapter<ReservasAdapter.ViewHolder>() {

    class ViewHolder(val binding: CardReservasguardadasBinding): RecyclerView.ViewHolder(binding.root){

        fun render(rese: Reservas){
            binding.direccionalojamiento.text = rese.descripcion
            binding.nombrealojamiento.text = rese.nombre
            Picasso.get().load(rese.imagen).into(binding.imagen)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CardReservasguardadasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(reservas[position])
    }

    override fun getItemCount(): Int =reservas.size
}