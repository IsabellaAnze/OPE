package br.com.aquarelauniformes.ope

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_funcoes.*

class FuncoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funcoes)

       // var toolbar = findViewById<Toolbar>(R.id.tbFuncoes)
       // setSupportActionBar(toolbar)

        val ss: String = intent.getStringExtra("key").toString()
        val titulo1 = findViewById<TextView>(R.id.fun_1)
        val titulo2 = findViewById<TextView>(R.id.fun_2)


        if (ss.equals("ESTOQUE")){
            titulo1.setText("Entrada")
            titulo2.setText("Saída")
        }else if (ss.equals("MATERIAL")){
            titulo1.setText("Tipo de Material")
            titulo2.setText("Classificação")
        }else{
            titulo1.setText("Contas a Pagar")
            titulo2.setText("Contas a Receber")
        }

        supportActionBar?.title = ss
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_activity, menu)

        val searchItem = menu?.findItem(R.id.action_buscar)
        val searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.action_buscar)) as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == android.R.id.home) {
            finish()
        }
        else if (id == R.id.action_atualizar) {
            //val action_atualizar = findViewById<Button>(R.id.action_atualizar)
            // val btnStartProgress = action_atualizar
            val pgbar = findViewById<ProgressBar>(R.id.pgbar)
            val progressBar: ProgressBar = pgbar

            Thread(Runnable {
                this@FuncoesActivity.runOnUiThread(Runnable {
                    progressBar?.visibility = View.VISIBLE
                })
                try {
                    Thread.sleep(10000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                this@FuncoesActivity.runOnUiThread(Runnable {
                    progressBar?.visibility = View.GONE
                })
            }).start()
        }
        else if (id == R.id.action_config) {
            //       Toast.makeText(this, "Botão de configuracoes",
            //Toast.LENGTH_LONG).show()

            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }
}