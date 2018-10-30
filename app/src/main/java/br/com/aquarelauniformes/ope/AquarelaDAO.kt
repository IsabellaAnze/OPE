package br.com.aquarelauniformes.ope


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface AquarelaDAO {


    @Query("SELECT * FROM aquarela where id = :id")
    fun getById(id: Long): Aquarela?

    @Query("SELECT * FROM aquarela")
    fun findAll(): List<Aquarela>

    @Insert
    fun insert(aquarela: Aquarela)

    @Delete
    fun delete(aquarela: Aquarela)

}