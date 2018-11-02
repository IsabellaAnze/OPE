package br.com.aquarelauniformes.ope

import android.content.Context
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.net.URL



object AquarelaService {

    //TROQUE PELO IP DE ONDE ESTÁ O WS
    val host = "http://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getAquarela(context: Context): List<Aquarela> {
        var aquarela = ArrayList<Aquarela>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/aquarela"
            val json = HttpHelper.get(url)
            aquarela = parserJson(json)
            // salvar offline
            for (d in aquarela) {
                saveOffline(d)
            }
            return aquarela
        } else {
            val dao = DatabaseManager.getAquarelaDAO()
            val aquarela = dao.findAll()
            return aquarela
        }

    }

    fun getAquarela(context: Context, id: Long): Aquarela? {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/aquarela/${id}"
            val json = HttpHelper.get(url)
            val aquarela = parserJson<Aquarela>(json)

            return aquarela
        } else {
            val dao = DatabaseManager.getAquarelaDAO()
            val aquarela = dao.getById(id)
            return aquarela
        }

    }

    fun save(aquarela: Aquarela): Response {
        val json = HttpHelper.post("$host/aquarela", aquarela.toJson())
        return parserJson(json)
    }

    fun saveOffline(aquarela: Aquarela): Boolean {
        val dao = DatabaseManager.getAquarelaDAO()

        if (!existeAquarela(aquarela)) {
            dao.insert(aquarela)
        }

        return true

    }

    fun existeAquarela(aquarela: Aquarela): Boolean {
        val dao = DatabaseManager.getAquarelaDAO()
        return dao.getById(aquarela.id) != null
    }

    fun delete(aquarela: Aquarela): Response {
        if (AndroidUtils.isInternetDisponivel(LMSApplication.getInstance().applicationContext)) {
            val url = "$host/aquarela/${aquarela.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getAquarelaDAO()
            dao.delete(aquarela)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson<T>(json, type)
    }
}