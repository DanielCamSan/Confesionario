package edu.bo.confesionario.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.bo.confesionario.Comment
import edu.bo.confesionario.R
import java.util.*

class CommentViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val comment_username = view.findViewById<TextView>(R.id.username)
    val comment_body = view.findViewById<TextView>(R.id.commentBody)
    val comment_date = view.findViewById<TextView>(R.id.commentDate)

    fun render(commentModel: Comment){
        var commentDate = commentModel.commentDate.get(Calendar.YEAR).toString()
        commentDate = commentDate + "-" + (if (commentModel.commentDate.get(Calendar.MONTH)<10) "0" else "") + commentModel.commentDate.get(Calendar.MONTH).toString()
        commentDate = commentDate + "-" + (if (commentModel.commentDate.get(Calendar.DAY_OF_MONTH)<10) "0" else "") + commentModel.commentDate.get(Calendar.DAY_OF_MONTH).toString()

        comment_username.text = commentModel.username
        comment_body.text = commentModel.commentBody
        comment_date.text = commentDate
    }
}