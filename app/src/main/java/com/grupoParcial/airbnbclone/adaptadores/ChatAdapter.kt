package com.grupoParcial.airbnbclone.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.databinding.CardMensajesBinding
import com.grupoParcial.airbnbclone.model.Chat_loby
import com.squareup.picasso.Picasso

class ChatAdapter(val Chats: List<Chat_loby>, private val listener: OnItemCLick) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    interface OnItemCLick {
        fun onItemClick(
            position: Int,
            nombre: String,
            mensaje: String,
            hora: String
        )
    }

    inner class ViewHolder(val binding: CardMensajesBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

        fun render(C: Chat_loby) {
            Picasso.get().load(C.Foto).into(binding.Foto)
            binding.Persona.text = C.Nombre
            binding.Mensaje.text = C.Mensaje
            binding.Hora.text = C.Hora

        }

        init {

            itemView.setOnClickListener(this)
        }


        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition


            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(
                    position,
                    binding.Persona.text.toString(),
                    binding.Mensaje.text.toString(),
                    binding.Hora.text.toString()
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CardMensajesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(Chats[position])
    }

    override fun getItemCount(): Int = Chats.size
}