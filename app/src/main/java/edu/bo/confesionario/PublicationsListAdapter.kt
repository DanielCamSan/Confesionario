package edu.bo.confesionario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PublicationsListAdapter(val publications: ArrayList<Publication>):
    RecyclerView.Adapter<PublicationsListAdapter.PublicationListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.publication_card, parent, false)
        return PublicationListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return publications.count()
    }

    override fun onBindViewHolder(holder: PublicationListViewHolder, position: Int) {

        val publication = publications.get(position)
        holder.itemView.findViewById<TextView>(R.id.categoryText).text = publication.category
        holder.itemView.findViewById<TextView>(R.id.titleText).text = publication.title
        holder.itemView.findViewById<TextView>(R.id.descriptionText).text = publication.description
        holder.itemView.findViewById<TextView>(R.id.numberText).text = "Confesion #"+publication.number
        holder.itemView.findViewById<TextView>(R.id.numberComentariesText).text = "5"
        holder.itemView.findViewById<TextView>(R.id.dateText).text = publication.date.toString()
    }

    class PublicationListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}