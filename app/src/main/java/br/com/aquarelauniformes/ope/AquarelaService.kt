package br.com.aquarelauniformes.ope

import android.content.Context

object AquarelaService {
    fun getAquarelas(contexto: Context): List<Aquarela> {
        val aquarela = mutableListOf<Aquarela>()
        for (i in 1..10) {
            val a = Aquarela()
            a.nome = ""
            a.tipo = "Tipo Produto"
            a.quantidade = "Quantidade"
            a.foto = "https://s3-sa-east-1.amazonaws.com/portfolio-usuario/fullsize%2F2017%2F04%2F13%2F404736_005515476_1523457946.png"
            aquarela.add(a)
        }
        return aquarela
    }

}