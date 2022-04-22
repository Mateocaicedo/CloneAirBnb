package com.grupoParcial.airbnbclone.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.databinding.CardAnterioresBinding
import com.grupoParcial.airbnbclone.databinding.CardReservasguardadasBinding
import com.grupoParcial.airbnbclone.model.Reservas
import com.squareup.picasso.Picasso

class PerfilAlojamientos (val Alojamiento:List<Reservas>): RecyclerView.Adapter<PerfilAlojamientos.ViewHolder>() {

    class ViewHolder(val binding: CardAnterioresBinding): RecyclerView.ViewHolder(binding.root){

        fun render(rese: Reservas){
            binding.direccionalojamiento.text = rese.descripcion
            binding.nombrealojamiento.text = rese.nombre
            Picasso.get().load(rese.imagen).into(binding.imagen)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CardAnterioresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(Alojamiento[position])
    }

    override fun getItemCount(): Int =Alojamiento.size
}