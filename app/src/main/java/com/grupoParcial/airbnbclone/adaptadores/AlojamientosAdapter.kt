package com.grupoParcial.airbnbclone.adaptadores


import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.R
import com.grupoParcial.airbnbclone.model.Alojamientos
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class AlojamientosAdapter(val alojamientos:List<Alojamientos>, private val listener: OnItemCLick
):RecyclerView.Adapter<AlojamientosAdapter.ViewHolder>() {

    interface  OnItemCLick{
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var nombre:TextView =itemView.findViewById(R.id.nombresearch)
        var precio:TextView =itemView.findViewById(R.id.preciosearch)
        var descripcion:TextView =itemView.findViewById(R.id.descripcionsearch)
        var image: ImageCarousel =itemView.findViewById(R.id.imagensearch)
        var direccion:TextView =itemView.findViewById(R.id.direccionsearch)

       init {

           itemView.setOnClickListener (this)
       }

        override fun onClick(p0: View?) {
            val position=absoluteAdapterPosition
            if (position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.resultaods_search, parent, false)
        return ViewHolder(v)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        


        holder.descripcion.text = alojamientos[position].Descripcion

        holder.nombre.text = alojamientos[position].Nombre
        holder.direccion.text = alojamientos[position].Descripcion
        holder.precio.text = (alojamientos[position].Precio.toString() + "/Noche")
        holder.image.addData(alojamientos[position].Imagen)


    }

    override fun getItemCount(): Int =alojamientos.size


}