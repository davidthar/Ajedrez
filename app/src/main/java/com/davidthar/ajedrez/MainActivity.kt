package com.davidthar.ajedrez

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.davidthar.ajedrez.databinding.ActivityMainBinding





//TODO COMO MARCAR PRIMERA FICHA EN EL RANGO
//TODO COMO MATAR
//TODO COMO MOVER 1 VEZ CADA UNO
//TODO COMO HACER JAQUE
//TODO COMO HACER JAQUE MATE
//TODO QUITAR ERROR CABALLO
//TODO ARREGLAR DIAGONALES BORDES PEONES


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var piezaSeleccionada : ImageView? = null

        val tablero = arrayOf(
                arrayOf(binding.a8,binding.b8,binding.c8,binding.d8,binding.e8,binding.f8,binding.g8,binding.h8),
                arrayOf(binding.a7,binding.b7,binding.c7,binding.d7,binding.e7,binding.f7,binding.g7,binding.h7),
                arrayOf(binding.a6,binding.b6,binding.c6,binding.d6,binding.e6,binding.f6,binding.g6,binding.h6),
                arrayOf(binding.a5,binding.b5,binding.c5,binding.d5,binding.e5,binding.f5,binding.g5,binding.h5),
                arrayOf(binding.a4,binding.b4,binding.c4,binding.d4,binding.e4,binding.f4,binding.g4,binding.h4),
                arrayOf(binding.a3,binding.b3,binding.c3,binding.d3,binding.e3,binding.f3,binding.g3,binding.h3),
                arrayOf(binding.a2,binding.b2,binding.c2,binding.d2,binding.e2,binding.f2,binding.g2,binding.h2),
                arrayOf(binding.a1,binding.b1,binding.c1,binding.d1,binding.e1,binding.f1,binding.g1,binding.h1)
        )

        tablero.colocaPiezas()

        fun asigna(tablero : Array<Array<ImageView>>){
            for(i in 0..7){
                for(j in 0..7){



                    //PEON NEGRO
                    if(tablero[i][j].tag == peonN){
                        tablero[i][j].setOnClickListener {
                            tablero.quitaMarcas()
                            piezaSeleccionada = tablero[i][j]

                            for(m in 1..1){
                                //Marca diagonales si hay pieza que se puede matar
                                if(i+m<=7 && j-1>=0
                                        && tablero[i+m][j-1].color()!=piezaSeleccionada!!.color()
                                        && !tablero[i+m][j-1].vacio()) marca(tablero[i+m][j-1])

                                if(i+m<=7 &&j+1<=7
                                        && tablero[i+m][j+1].color()!=piezaSeleccionada!!.color()
                                        && !tablero[i+m][j+1].vacio()) marca(tablero[i+m][j+1])

                                //Marca la siguiente, y 2 si esta en el inicio
                                if(i+m<=7 && tablero[i+m][j].vacio()) marca(tablero[i+m][j]) else break
                                if(i+2<=7 && i==1 && tablero[i+2][j].vacio()) marca(tablero[i+2][j])
                            }
                            asigna(tablero)
                        }
                    }

                    //PEON BLANCO
                    else if(tablero[i][j].tag == peonB){
                        tablero[i][j].setOnClickListener {
                            tablero.quitaMarcas()
                            piezaSeleccionada = tablero[i][j]

                            for(m in 1..1){
                                //Marca diagonales si hay pieza que se puede matar
                                if(i-m>=0 && j-1>=0
                                        && tablero[i-m][j-1].color()!=piezaSeleccionada!!.color()
                                        && !tablero[i-m][j-1].vacio()) marca(tablero[i-m][j-1])

                                if(i-m>=0 && j+1<=7
                                        && tablero[i-m][j+1].color()!=piezaSeleccionada!!.color()
                                        && !tablero[i-m][j+1].vacio()) marca(tablero[i-m][j+1])

                                //Marca la siguiente, y 2 si esta en el inicio
                                if(i-m>=0 && tablero[i-m][j].vacio()) marca(tablero[i-m][j]) else break
                                if(i-2>=0 && i==6 && tablero[i-2][j].vacio()) marca(tablero[i-2][j])
                            }
                            asigna(tablero)
                        }
                    }


                    //TORRES
                    else if(tablero[i][j].tag == torreB || tablero[i][j].tag == torreN){
                        tablero[i][j].setOnClickListener{
                            tablero.quitaMarcas()
                            piezaSeleccionada = tablero[i][j]
                            for(m in 1..7){
                                if((j-m)>=0 && tablero[i][j-m].vacio()) marca(tablero[i][j-m])
                                else{
                                    if((j-m)>=0 && tablero[i][j-m].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i][j-m])
                                    } else break
                                    break
                                }
                            }
                            for(m in 1..7){
                                if((i-m)>=0 && tablero[i-m][j].vacio()) marca(tablero[i-m][j])
                                else{
                                    if((i-m)>=0 && tablero[i-m][j].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i-m][j])
                                    } else break
                                    break
                                }
                            }
                            for(m in 1..7){
                                if((j+m)<=7 && tablero[i][j+m].vacio()) marca(tablero[i][j+m])
                                else{
                                    if((j+m)<=7 && tablero[i][j+m].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i][j+m])
                                    } else break
                                    break
                                }
                            }
                            for(m in 1..7){
                                if((i+m)<=7 && tablero[i+m][j].vacio()) marca(tablero[i+m][j])
                                else{
                                    if((i+m)<=7 && tablero[i+m][j].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i+m][j])
                                    } else break
                                    break
                                }
                            }

                            asigna(tablero)
                        }
                    }

                    //CABALLOS
                    else if(tablero[i][j].tag == caballoB || tablero[i][j].tag == caballoN){
                        tablero[i][j].setOnClickListener {
                            tablero.quitaMarcas()
                            piezaSeleccionada = tablero[i][j]
                            if((i-2)>=0 && (j-1)>=0 && tablero[i-2][j-1].vacio()) marca(tablero[i-2][j-1])
                            if((i-2)>=0 && (j+1)<=7 && tablero[i-2][j+1].vacio()) marca(tablero[i-2][j+1])
                            if((i-1)>=0 && (j-2)>=0 && tablero[i-1][j-2].vacio()) marca(tablero[i-1][j-2])
                            if((i-1)>=0 && (j+2)<=7 && tablero[i-1][j+2].vacio()) marca(tablero[i-1][j+2])
                            if((i+1)<=7 && (j-2)>=0 && tablero[i+1][j-2].vacio()) marca(tablero[i+1][j-2])
                            if((i+1)<=7 && (j+2)<=7 && tablero[i+1][j+2].vacio()) marca(tablero[i+1][j+2])
                            if((i+2)<=7 && (j-1)>=0 && tablero[i+2][j-1].vacio()) marca(tablero[i+2][j-1])
                            if((i+2)<=7 && (j+1)>=0 && tablero[i+2][j+1].vacio()) marca(tablero[i+2][j+1])

                            asigna(tablero)

                        }
                    }

                    //ALFILES
                    else if(tablero[i][j].tag == alfilB || tablero[i][j].tag == alfilN){
                        tablero[i][j].setOnClickListener {
                            tablero.quitaMarcas()
                            piezaSeleccionada = tablero[i][j]

                            for(m in 1..7){
                                if(i-m>=0 && j-m>=0 && tablero[i-m][j-m].vacio()) marca(tablero[i-m][j-m])
                                else{
                                    if(i-m>=0 && j-m>=0 && tablero[i-m][j-m].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i-m][j-m])
                                    } else break
                                    break
                                }
                            }
                            for(m in 1..7){
                                if(i+m<=7 && j+m<=7 && tablero[i+m][j+m].vacio()) marca(tablero[i+m][j+m])
                                else{
                                    if(i+m<=7 && j+m<=7 && tablero[i+m][j+m].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i+m][j+m])
                                    } else break
                                    break
                                }
                            }
                            for(m in 1..7){
                                if(i-m>=0 && j+m<=7 && tablero[i-m][j+m].vacio()) marca(tablero[i-m][j+m])
                                else{
                                    if(i-m>=0 && j+m<=7 && tablero[i-m][j+m].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i-m][j+m])
                                    } else break
                                    break
                                }
                            }
                            for(m in 1..7){
                                if(i+m<=7 && j-m>=0 && tablero[i+m][j-m].vacio()) marca(tablero[i+m][j-m])
                                else{
                                    if(i+m<=7 && j-m>=0 && tablero[i+m][j-m].color() != piezaSeleccionada!!.color()){
                                        marca(tablero[i+m][j-m])
                                    } else break
                                    break
                                }
                            }
                            asigna(tablero)

                        }

                    }

                    //REINA
                    else if(tablero[i][j].tag == reinaB || tablero[i][j].tag == reinaN){
                        tablero[i][j].setOnClickListener{
                            tablero.quitaMarcas()
                            piezaSeleccionada = tablero[i][j]
                            for(m in 1..7){
                                if((j-m)>=0 && tablero[i][j-m].vacio()) marca(tablero[i][j-m]) else break
                            }
                            for(m in 1..7){
                                if((i-m)>=0 && tablero[i-m][j].vacio()) marca(tablero[i-m][j]) else break
                            }
                            for(m in 1..7){
                                if((j+m)<=7 && tablero[i][j+m].vacio()) marca(tablero[i][j+m]) else break
                            }
                            for(m in 1..7){
                                if((i+m)<=7 && tablero[i+m][j].vacio()) marca(tablero[i+m][j]) else break
                            }
                            for(m in 1..7){
                                if(i-m>=0 && j-m>=0 && tablero[i-m][j-m].vacio()) marca(tablero[i-m][j-m]) else break
                            }
                            for(m in 1..7){
                                if(i+m<=7 && j+m<=7 && tablero[i+m][j+m].vacio()) marca(tablero[i+m][j+m]) else break
                            }
                            for(m in 1..7){
                                if(i-m>=0 && j+m<=7 && tablero[i-m][j+m].vacio()) marca(tablero[i-m][j+m]) else break
                            }
                            for(m in 1..7){
                                if(i+m<=7 && j-m>=0 && tablero[i+m][j-m].vacio()) marca(tablero[i+m][j-m]) else break
                            }

                            asigna(tablero)

                        }
                    }


                    //REY
                    else if(tablero[i][j].tag == reyB || tablero[i][j].tag == reyN){
                        tablero[i][j].setOnClickListener {
                            tablero.quitaMarcas()
                            piezaSeleccionada = tablero[i][j]
                            //Rectas
                            if(i-1>=0 && tablero[i-1][j].vacio()) marca(tablero[i-1][j])
                            if(i+1<=7 && tablero[i+1][j].vacio()) marca(tablero[i+1][j])
                            if(j-1>=0 && tablero[i][j-1].vacio()) marca(tablero[i][j-1])
                            if(j+1<=7 && tablero[i][j+1].vacio()) marca(tablero[i][j+1])
                            //Diagonales
                            if(i-1>=0 && j-1>=0 && tablero[i-1][j-1].vacio()) marca(tablero[i-1][j-1])
                            if(i+1<=7 && j+1<=7 && tablero[i+1][j+1].vacio()) marca(tablero[i+1][j+1])
                            if(i-1>=0 && j+1<=7 && tablero[i-1][j+1].vacio()) marca(tablero[i-1][j+1])
                            if(i+1<=7 && j-1>=0 && tablero[i+1][j-1].vacio()) marca(tablero[i+1][j-1])


                            asigna(tablero)
                        }

                    }
                    //CASILLA VACIA
                    else tablero[i][j].setOnClickListener {  }





                    //CASILLA MARCADA
                    if(tablero[i][j].estaMarcada()){
                        tablero[i][j].setOnClickListener {
                            tablero[i][j].coloca(piezaSeleccionada?.tag as Int)
                            piezaSeleccionada?.tag = null
                            piezaSeleccionada?.setImageResource(0)
                            tablero.quitaMarcas()
                            asigna(tablero)
                        }
                    }






                }
            }
        }


        asigna(tablero)
    }



    fun Array<Array<ImageView>>.colocaPiezas(){
        this[0][0].coloca(torreN)
        this[0][7].coloca(torreN)

        this[0][1].coloca(caballoN)
        this[0][6].coloca(caballoN)

        this[0][2].coloca(alfilN)
        this[0][5].coloca(alfilN)

        this[0][3].coloca(reinaN)
        this[0][4].coloca(reyN)

        this[1].forEach {
            it.coloca(peonN)
        }

        this[7][0].coloca(torreB)
        this[7][7].coloca(torreB)

        this[7][1].coloca(caballoB)
        this[7][6].coloca(caballoB)

        this[7][2].coloca(alfilB)
        this[7][5].coloca(alfilB)

        this[7][3].coloca(reinaB)
        this[7][4].coloca(reyB)

        this[6].forEach {
            it.coloca(peonB)
        }
    }

    fun marca(imageView: ImageView){
        var drawable = imageView.background as ColorDrawable

        if(drawable.color == ContextCompat.getColor(this,R.color.negro))
            imageView.setBackgroundColor(ContextCompat.getColor(this,R.color.negro_marcado))
        else if(drawable.color == ContextCompat.getColor(this,R.color.blanco))
            imageView.setBackgroundColor(ContextCompat.getColor(this,R.color.blanco_marcado))
    }

    fun ImageView.estaMarcada() : Boolean{
        var drawable = this.background as ColorDrawable

        if(drawable.color == ContextCompat.getColor(context,R.color.negro_marcado) ||
                drawable.color == ContextCompat.getColor(context,R.color.blanco_marcado)){
            return true
        }

        return false
    }

    fun ImageView.color() : Int{
        var piezasBlancas = arrayOf(peonB,torreB,caballoB,alfilB,reyB,reinaB)
        var piezasNegras = arrayOf(peonN,torreN,caballoN,alfilN,reyN,reinaN)

        for(i in piezasBlancas){
            if(this.tag == i) return 1
        }
        for(i in piezasNegras){
            if(this.tag == i) return 2
        }

        return 0
    }

    fun ImageView.coloca(pieza: Int){
        this.setImageResource(pieza)
        this.tag = pieza
    }

    fun ImageView.vacio(): Boolean{
        return this.tag == null
    }

    fun Array<Array<ImageView>>.quitaMarcas(){
        var casillasNegras = arrayOf(
                this[0][1],this[0][3],this[0][5],this[0][7],this[1][0],this[1][2],this[1][4],this[1][6],
                this[2][1],this[2][3],this[2][5],this[2][7],this[3][0],this[3][2],this[3][4],this[3][6],
                this[4][1],this[4][3],this[4][5],this[4][7],this[5][0],this[5][2],this[5][4],this[5][6],
                this[6][1],this[6][3],this[6][5],this[6][7],this[7][0],this[7][2],this[7][4],this[7][6]
        )
        var casillasBlancas = arrayOf(
                this[0][0],this[0][2],this[0][4],this[0][6],this[1][1],this[1][3],this[1][5],this[1][7],
                this[2][0],this[2][2],this[2][4],this[2][6],this[3][1],this[3][3],this[3][5],this[3][7],
                this[4][0],this[4][2],this[4][4],this[4][6],this[5][1],this[5][3],this[5][5],this[5][7],
                this[6][0],this[6][2],this[6][4],this[6][6],this[7][1],this[7][3],this[7][5],this[7][7]
        )

        casillasBlancas.forEach { it.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.blanco)) }
        casillasNegras.forEach { it.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.negro)) }
    }


    //RECURSOS DE PIEZAS
    val peonB = R.drawable.white_pawn
    val torreB = R.drawable.white_rook
    val caballoB = R.drawable.white_knight
    val alfilB = R.drawable.white_bishop
    val reyB = R.drawable.white_king
    val reinaB = R.drawable.white_queen

    val peonN = R.drawable.black_pawn
    val torreN = R.drawable.black_rook
    val caballoN = R.drawable.black_knight
    val alfilN = R.drawable.black_bishop
    val reyN = R.drawable.black_king
    val reinaN = R.drawable.black_queen

}