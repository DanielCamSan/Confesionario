package edu.bo.framework

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.bo.data.IRemoteDataSource
import edu.bo.domain.Publication as DomainPublication
import java.util.*


class DatabaseRef : IRemoteDataSource {

    private var listResult = arrayListOf<Publication>()
    private var database = FirebaseDatabase.getInstance().getReference().child("publications")
    constructor(){
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snap in dataSnapshot.children) {
                    var date = Calendar.getInstance()
                    date.set(2,2,2)
                    val publication = Publication(
                        snap.child("category").value.toString(),
                        snap.child("id").value.toString(),
                        snap.child("title").value.toString(),
                        snap.child("description").value.toString(),
                        snap.child("date").value.toString(),
                        snap.child("idUser").value.toString()
                    )
                    listResult.add(publication)
                    Log.d("s1", snap.child("id").value.toString())
                    Log.d("s1", snap.child("title").value.toString())
                    Log.d("s1", snap.child("description").value.toString())
                    Log.d("s1", snap.child("date").value.toString())
                    Log.d("s1", snap.child("idUser").value.toString())

                    Log.d("erer",listResult.count().toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Error", "Fallo al recuperar las publicaciones.", error.toException())
            }
        })
    }
    override suspend fun getPublications(): List<DomainPublication> {
        var listDomains = listResult.map {
            publication -> publication.toDomainPublication()
        }
        return listDomains
    }
    /*
    override fun createPublication(publication : DomainPublication)
    {
        val value = FirebaseDatabase.getInstance().getReference("publications")
        value.child("tsts").setValue(publication)
    }
    */
}