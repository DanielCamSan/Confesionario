package edu.bo.confesionario.publications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import edu.bo.confesionario.IndividualConfession
import edu.bo.confesionario.R
import edu.bo.confesionario.adapter.RedirectToPublicationAdapter
import java.util.*
import kotlin.collections.ArrayList
import edu.bo.domain.Publication

class PublicationsListAdapter(val publications: ArrayList<Publication>):
    RecyclerView.Adapter<PublicationsListAdapter.PublicationListViewHolder>() {
    private lateinit var redirectAdapter : RedirectToPublicationAdapter
    private lateinit var parentContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationListViewHolder {
        parentContext = parent.context
        redirectAdapter = RedirectToPublicationAdapter(parentContext)
        val v = LayoutInflater.from(parent.context).inflate(R.layout.publication_card, parent, false)
        return PublicationListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return publications.count()
    }
    override fun onBindViewHolder(holder: PublicationListViewHolder, position: Int) {

        val publication = publications.get(position)
        var date = publication.date.get(Calendar.YEAR).toString()
        date = date + "-" + (if (publication.date.get(Calendar.MONTH)<10) "0" else "") + publication.date.get(Calendar.MONTH).toString()
        date = date + "-" + (if (publication.date.get(Calendar.DAY_OF_MONTH)<10) "0" else "") + publication.date.get(Calendar.DAY_OF_MONTH).toString()
        holder.itemView.findViewById<TextView>(R.id.categoryText).text = publication.category
        holder.itemView.findViewById<TextView>(R.id.titleText).text = publication.title
        holder.itemView.findViewById<TextView>(R.id.descriptionText).text = publication.description
        holder.itemView.findViewById<TextView>(R.id.numberText).text = "Confesion #"+publication.id.toString()
        //holder.itemView.findViewById<TextView>(R.id.commentaries).text = publication.numberOfCommentaries.toString()
        holder.itemView.findViewById<TextView>(R.id.dateText).text = date
        holder.itemView.findViewById<TextView>(R.id.userText).text = publication.userName
        date = publication.date.get(Calendar.YEAR).toString()
        date = date + "-" + (if (publication.date.get(Calendar.MONTH)+1<10) "0" else "") + (publication.date.get(Calendar.MONTH)+1).toString()
        date = date + "-" + (if (publication.date.get(Calendar.DAY_OF_MONTH)<10) "0" else "") + publication.date.get(Calendar.DAY_OF_MONTH).toString()
        val bundle = redirectAdapter.getBundleOfPublication(publication)
        holder.itemView.findViewById<CardView>(R.id.publication_card).setOnClickListener {
            redirectAdapter.redirectWith(bundle)
        }
    }

    class PublicationListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}