package br.com.aquarelauniformes.ope

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Aquarela::class), version = 1)
abstract class LMSDatabase: RoomDatabase() {
    abstract fun aquarelaDAO(): AquarelaDAO
}