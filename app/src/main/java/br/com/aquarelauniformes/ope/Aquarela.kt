package br.com.aquarelauniformes.ope

import java.io.Serializable

class Aquarela : Serializable {
    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    override fun toString(): String {
        return "Aquarela(nome='$nome')"
    }
}