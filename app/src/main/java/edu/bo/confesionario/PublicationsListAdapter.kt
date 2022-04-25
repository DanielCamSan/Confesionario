package edu.bo.confesionario

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class PublicationsListAdapter(val publications: ArrayList<Publication>):
    RecyclerView.Adapter<PublicationsListAdapter.PublicationListViewHolder>() {
    private lateinit var parentContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationListViewHolder {
        parentContext = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.publication_card, parent, false)
        return PublicationListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return publications.count()
    }

    override fun onBindViewHolder(holder: PublicationListViewHolder, position: Int) {

        val publication = publications.get(position)
        var date = publication.date.get(Calendar.YEAR).toString() + "-"+publication.date.get(Calendar.MONTH).toString()+"-"+ publication.date.get(Calendar.DAY_OF_MONTH).toString()
        holder.itemView.findViewById<TextView>(R.id.categoryText).text = publication.category
        holder.itemView.findViewById<TextView>(R.id.titleText).text = publication.title
        holder.itemView.findViewById<TextView>(R.id.descriptionText).text = publication.description
        holder.itemView.findViewById<TextView>(R.id.numberText).text = "Confesion #"+publication.number
        holder.itemView.findViewById<TextView>(R.id.numberComentariesText).text = "5"
        holder.itemView.findViewById<TextView>(R.id.dateText).text = date
        holder.itemView.findViewById<TextView>(R.id.userText).text = publication.userName
        val bundle = Bundle()
        bundle.putString("category", publication.category)
        bundle.putString("title", publication.title)
        bundle.putString("description", publication.description)
        bundle.putString("number", publication.number)
        bundle.putString("date", date)
        bundle.putString("userName", publication.userName)
        holder.itemView.findViewById<CardView>(R.id.publication_card).setOnClickListener {
            var intent = Intent(parentContext, IndividualConfession::class.java)
            intent.putExtras(bundle)
            startActivity(parentContext, intent, bundle)
        }
    }

    class PublicationListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}