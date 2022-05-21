package edu.bo.framework

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.bo.data.IFirebaseCommentsDataSource
import edu.bo.framework.Comment as Comment
import java.text.SimpleDateFormat
import java.util.*
import edu.bo.domain.Comment as CommentDTO


class CommentDataSource (val commentList: MutableList<CommentDTO?>, val idPublication: Int?) : IFirebaseCommentsDataSource {
    override suspend fun getComments(): List<CommentDTO?> {
        Log.d("id Publication", idPublication.toString())
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Comments")
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(comment in snapshot.children){
                    val snapcomment = comment.getValue(Comment::class.java)
                    if(snapcomment?.idPublication == idPublication){
                        commentList.add(getDTO(snapcomment))
                    }
                }
            }
            override fun onCancelled(p0: DatabaseError) {}
        })
        return commentList
    }

    override suspend fun postComment(newComment: CommentDTO) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Comments")

        myRef.child(newComment.id).setValue(getDomain(newComment))
    }
    private fun getDTO(comment: Comment?): CommentDTO{
        if(comment != null) {
            val date = Calendar.getInstance()
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            date.time = sdf.parse(comment.commentDate)
            return CommentDTO(comment.id, comment.username, comment.idUser, comment.idPublication, comment.commentBody, date)
        }
        return CommentDTO("","","",0,"",Calendar.getInstance())
    }
    private fun getDomain(comment: CommentDTO?): Comment{
        if(comment != null) {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            return Comment(comment.id, comment.username, comment.idUser, comment.idPublication, comment.commentBody, sdf.format(comment.commentDate.time))
        }
        return Comment("","","",0,"","")
    }
}