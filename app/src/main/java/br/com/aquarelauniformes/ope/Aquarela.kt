package br.com.aquarelauniformes.ope

import java.io.Serializable

class Aquarela : Serializable {
    var id:Long = 0
    var nome = ""
    var tipo = ""
    var quantidade = ""
    var foto = ""
    override fun toString(): String {
        return "Aquarela(nome='$nome')"
    }
}