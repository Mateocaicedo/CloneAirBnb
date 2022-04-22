package com.grupoParcial.airbnbclone.adaptadores


import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.menu.MenuView
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.grupoParcial.airbnbclone.R
import com.grupoParcial.airbnbclone.model.Alojamientos
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class AlojamientosAdapter(val alojamientos:List<Alojamientos>, private val listener: OnItemCLick
):RecyclerView.Adapter<AlojamientosAdapter.ViewHolder>() {

    interface  OnItemCLick{
        fun onItemClick(position: Int, nombre: String, precio:String, desc:String,dire:String, fotos:ArrayList<String?>)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {

        val nombre:TextView =itemView.findViewById(R.id.nombresearch)
        val precio:TextView =itemView.findViewById(R.id.preciosearch)
        val descripcion:TextView =itemView.findViewById(R.id.descripcionsearch)
        val image: ImageCarousel =itemView.findViewById(R.id.imagensearch)

        val direccion:TextView =itemView.findViewById(R.id.direccionsearch)


       init {

           itemView.setOnClickListener (this)
       }

        override fun onClick(p0: View?) {
            val position=absoluteAdapterPosition


            val arrayList = ArrayList<String?>()

            for (item in alojamientos[position].Imagen){
                arrayList.add(item.imageUrl)
            }


            if (position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position, nombre.text.toString(), precio.text.toString(), descripcion.text.toString(),  direccion.text.toString(), arrayList)
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
        holder.precio.text = ("$"+alojamientos[position].Precio.toString() + "/Noche")
        holder.image.addData(alojamientos[position].Imagen)


    }

    override fun getItemCount(): Int =alojamientos.size


}