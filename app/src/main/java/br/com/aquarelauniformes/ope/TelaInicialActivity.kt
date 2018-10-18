package br.com.aquarelauniformes.ope

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.Toast
import android.app.SearchManager
import android.content.Context
import android.support.v4.view.MenuItemCompat
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import br.com.aquarelauniformes.ope.Map.MapsActivity

import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

open class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var aquarela = listOf<Aquarela>()
    var recyclerAquarela: RecyclerView? = null

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val botaoSair = findViewById<Button>(R.id.botaoSair)
        botaoSair.setOnClickListener {cliqueSair()}

        val args:Bundle? = intent.extras
        val nome = args?.getString("nome")
        val numero = intent.getIntExtra("nome",0)
        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()



        var toolbar = findViewById<Toolbar>(R.id.tbFuncoes)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "OPE"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        recyclerAquarela = findViewById(R.id.recyclerAquarela)
        recyclerAquarela?.layoutManager = LinearLayoutManager(context)
        recyclerAquarela?.itemAnimator = DefaultItemAnimator()
        recyclerAquarela?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskAquarela()
    }

    fun taskAquarela() {
        this.aquarela = AquarelaService.getAquarelas(context)
        recyclerAquarela?.adapter = AquarelaAdapter(aquarela){onClickAquarela(it)}
    }

    fun onClickAquarela(aquarela: Aquarela) {
//    Toast.makeText(context, " Clicou na Aquarela ${aquarela.nome}",
        //          Toast.LENGTH_SHORT).show()
        val intent = Intent(this, AquarelaActivity::class.java)
        intent.putExtra("aquarela", aquarela)
        startActivity(intent)

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_forum -> {
                val intent = Intent(this, ForumActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_mensagens -> {
                val intent = Intent(this, MsgActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_config -> {
                val intent = Intent(this, ConfigActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_localizacao -> {
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)

            }
        }
        val drawer = findViewById<DrawerLayout>(R.id.menu_lateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun configuraMenuLateral(){
        var toolbar = tbFuncoes
        var menuLateral = findViewById<DrawerLayout>(R.id.layoutMenuLateral)


        var toogle = ActionBarDrawerToggle(
                this,
                menuLateral,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        var navigationView = menu_lateral
        navigationView.setNavigationItemSelectedListener (this)
    }


    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
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

        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Botão de buscar",
                    Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            //val action_atualizar = findViewById<Button>(R.id.action_atualizar)
            // val btnStartProgress = action_atualizar
            val pgbar = findViewById<ProgressBar>(R.id.pgbar)
            val progressBar: ProgressBar = pgbar

            Thread(Runnable {
                this@TelaInicialActivity.runOnUiThread(Runnable {
                    progressBar?.visibility = View.VISIBLE
                })
                try {
                    Thread.sleep(10000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                this@TelaInicialActivity.runOnUiThread(Runnable {
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

