package com.grupoParcial.airbnbclone.model

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

data class Alojamientos(val Nombre:String,
                        val Descripcion:String,
                        val Direccion:String,
                        val Precio:Int,
                        val Imagen:ArrayList<CarouselItem>
)
