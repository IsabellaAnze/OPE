package br.com.aquarelauniformes.ope

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro_aquarela.*

class AquarelaCadastroActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_aquarela)
        setTitle("Novo Produto")

        salvarAquarela.setOnClickListener {
            val aquarela = Aquarela()
            aquarela.nome = nomeAquarela.text.toString()
            aquarela.tipo = tipoAquarela.text.toString()
            aquarela.quantidade = quantidadeAquarela.text.toString()
            aquarela.foto = urlFoto.text.toString()

            taskAtualizar(aquarela)
        }
    }

    private fun taskAtualizar(aquarela: Aquarela) {

        Thread {
            AquarelaService.save(aquarela)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
