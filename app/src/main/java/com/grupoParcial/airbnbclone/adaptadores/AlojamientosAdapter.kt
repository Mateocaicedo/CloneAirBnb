package com.grupoParcial.airbnbclone.adaptadores


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.databinding.ResultaodsSearchBinding
import com.grupoParcial.airbnbclone.model.Alojamientos


class AlojamientosAdapter(val alojamientos:List<Alojamientos>):RecyclerView.Adapter<AlojamientosAdapter.ViewHolder>() {

    class ViewHolder(val binding: ResultaodsSearchBinding): RecyclerView.ViewHolder(binding.root){

        fun render(alojami: Alojamientos){
            binding.descripcion.text = alojami.Nombre
            binding.nombre.text = alojami.Nombre
            binding.direccion.text = alojami.Direccion
            binding.precio.text = alojami.Precio.toString()
            binding.imagen.addData(alojami.Imagen)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemBinding = ResultaodsSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(itemBinding)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(alojamientos[position])
    }

    override fun getItemCount(): Int =alojamientos.size
}