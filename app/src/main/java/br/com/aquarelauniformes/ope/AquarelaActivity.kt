package br.com.aquarelauniformes.ope

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class AquarelaActivity : DebugActivity() {

    var aquarela: Aquarela? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aquarela)

        // recuperar onjeto de Disciplina da Intent
        aquarela = intent?.getSerializableExtra("aquarela") as Aquarela

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.tbFuncoes)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = aquarela?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeAquarela)
        texto.text = aquarela?.nome
        var imagem = findViewById<ImageView>(R.id.imagemAquarela)
        Picasso.with(this).load(aquarela?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }
}
