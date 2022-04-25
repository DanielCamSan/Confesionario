package edu.bo.confesionario.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.bo.confesionario.Comment
import edu.bo.confesionario.R

class CommentViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val comment_username = view.findViewById<TextView>(R.id.username)
    val comment_body = view.findViewById<TextView>(R.id.commentBody)
    val comment_date = view.findViewById<TextView>(R.id.commentDate)

    fun render(commentModel: Comment){
        comment_username.text = commentModel.username
        comment_body.text = commentModel.commentBody
        comment_date.text = commentModel.commentDate.toString()
    }
}