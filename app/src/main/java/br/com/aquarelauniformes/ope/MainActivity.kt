package br.com.aquarelauniformes.ope

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.login_aquarela)
        

        val usuario = findViewById<EditText>(R.id.campo_usuario)
        val senha = findViewById<EditText>(R.id.campo_senha)
        val btn = findViewById<Button>(R.id.botao_login)

        btn.setOnClickListener { onClickLogin() }

    }

    fun onClickLogin(){
        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)

        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()

        if (valorUsuario.equals("aluno") && valorSenha.equals("impacta")){

            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        }else{
            campoSenha?.setError("Usuário ou senha incorretos")
        }
    }


}










