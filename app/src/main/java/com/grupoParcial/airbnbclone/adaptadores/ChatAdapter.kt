package com.grupoParcial.airbnbclone.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.databinding.CardMensajesBinding
import com.grupoParcial.airbnbclone.model.Chat_loby
import com.squareup.picasso.Picasso

class ChatAdapter (val Chat:List<Chat_loby>): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(val binding: CardMensajesBinding): RecyclerView.ViewHolder(binding.root){

        fun render(C: Chat_loby){
            Picasso.get().load(C.Foto).into(binding.Foto)
            binding.Persona.text=C.Nombre
            binding.Mensaje.text=C.Mensaje
            binding.Hora.text=C.Hora

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CardMensajesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(Chat[position])
    }

    override fun getItemCount(): Int =Chat.size
}