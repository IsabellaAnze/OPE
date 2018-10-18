package br.com.aquarelauniformes.ope

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class AquarelaAdapter(
        val aquarela: List<Aquarela>,
        val onClick: (Aquarela) -> Unit
    )
    : RecyclerView.Adapter<AquarelaAdapter.AquarelaViewHolder>() {

    class AquarelaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImagem: ImageView
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImagem = view.findViewById<ImageView>(R.id.cardImagem)
            cardView = view.findViewById<CardView>(R.id.card_aquarela)

        }

    }

    override fun getItemCount() = aquarela.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AquarelaViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.adapter_aquarela, p0, false)
        val holder = AquarelaViewHolder(view)
        return holder

    }


    override fun onBindViewHolder(p0: AquarelaViewHolder, p1: Int) {
        val contexto = p0.itemView.context
        val aquarela = aquarela[p1]

        p0.cardNome.text = aquarela.nome

        p0.itemView.setOnClickListener { onClick(aquarela) }
    }
}

