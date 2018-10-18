package br.com.aquarelauniformes.ope

import android.content.Context

object AquarelaService {
    fun getAquarelas(contexto: Context): List<Aquarela> {
        val aquarela = mutableListOf<Aquarela>()
        for (i in 1..10) {
            val a = Aquarela()
            a.nome = "Aquarela $i"
            a.ementa = "Ementa da aquarela $i"
            a.foto = "https://img.elo7.com.br/product/original/1776DCC/avental-preto-feminino-limpeza.jpg"
            aquarela.add(a)
        }
        return aquarela
    }

}