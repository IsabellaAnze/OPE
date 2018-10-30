package br.com.aquarelauniformes.ope

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "aquarela")
class Aquarela : Serializable {

    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var tipo = ""
    var quantidade = ""
    var foto = ""

    override fun toString(): String {
        return "Aquarela(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}