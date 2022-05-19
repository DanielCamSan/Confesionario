package edu.bo.framework

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.bo.data.IFirebaseCommentsDataSource
import edu.bo.domain.Comment
import java.util.*


class CommentDataSource (val commentList: MutableList<Comment?>) : IFirebaseCommentsDataSource {
    override suspend fun getComments(): List<Comment?> {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Comments")
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(comment in snapshot.children){
                    commentList.add(comment.getValue(Comment::class.java))
                    Log.d("comment -->", comment.getValue().toString())
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
        return commentList
    }

    fun getDate(year:Int,month:Int,day:Int) : Calendar
    {
        val date =  Calendar.getInstance()
        date.set(year, month, day)
        return date
    }

    fun getList(): List<Comment>{
        return listOf<Comment>(

            Comment("Justin1", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", getDate(2022,2,11)),
            Comment("Justin2", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", getDate(2022,2,11))
        )
    }
}