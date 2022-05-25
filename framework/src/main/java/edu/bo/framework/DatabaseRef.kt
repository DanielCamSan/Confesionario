package edu.bo.framework

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.bo.data.IRemoteDataSource
import java.text.SimpleDateFormat
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

    override suspend fun postPublication(publicationObject: DomainPublication) {
        //database = Firebase.database.reference
        //database.child("publications").child("example").setValue(publicationObject);
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("publications")
        myRef.child(publicationObject.id.toString()).setValue(getDomain(publicationObject))
    }
    private fun getDomain(publication: DomainPublication?): PublicationPublish {
        if(publication != null) {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            return PublicationPublish(publication.category, publication.title, publication.description, sdf.format(publication.date.time),publication.userName,publication.id)
        }
        return PublicationPublish("","","",Calendar.getInstance().toString(),"","0")
    }

}