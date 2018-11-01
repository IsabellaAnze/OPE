package br.com.aquarelauniformes.ope

import android.arch.persistence.room.Room
import br.com.aquarelauniformes.ope.lmsapp.LMSApplication


object DatabaseManager {

    // singleton
    private var dbInstance: LMSDatabase
    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                LMSDatabase::class.java, // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getAquarelaDAO(): AquarelaDAO {
        return dbInstance.aquarelaDAO()
    }
}