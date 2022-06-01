package edu.bo.framework

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.bo.data.IRemoteDataSource
import java.text.SimpleDateFormat
import java.util.*
import edu.bo.domain.Comment as CommentDTO
import edu.bo.domain.Publication as DomainPublication


class DatabaseRef : IRemoteDataSource {

    private var listResult = arrayListOf<Publication>()
    private var database = FirebaseDatabase.getInstance().getReference().child("publications")
    private var commentariesReference = CommentDataSource(mutableListOf<CommentDTO?>(),0)

    private fun getPublicationFormat(snap:DataSnapshot) : Publication
    {
        val publication = Publication(
            snap.child("category").value.toString(),
            snap.child("id").value.toString(),
            snap.child("title").value.toString(),
            snap.child("description").value.toString(),
            snap.child("date").value.toString(),
            snap.child("userName").value.toString()
        )
        return publication
    }
    suspend fun fetchPublications(): List<Publication> {
        listResult.clear()
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("publications")
        reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(publication in snapshot.children){
                    listResult.add(getPublicationFormat(publication))
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("Error", "Fallo al recuperar las publicaciones.", error.toException())
            }
        })
        val dataSnapshotTask = reference.get()
        //dataSnapshotTask
        Thread.sleep(3_000)
        val datas = dataSnapshotTask.result;
        for(publication in datas.children){
            listResult.add(getPublicationFormat(publication))
        }


        return  listResult
    }
    override suspend fun getPublications(): List<DomainPublication> {
        val commentaries =  commentariesReference.getAllComments()
        val publications = fetchPublications()
        var listDomains = publications.map {
            publication -> publication.toDomainPublication()
        }
        for (commentary in commentaries)
        {
            for (publication in listDomains)
            {
                if (commentary != null) {
                    if (publication.id == commentary.idPublication.toString()) {
                        publication.numberOfCommentaries += 1
                        break
                    }
                }
            }
        }

        return listDomains
    }

    override suspend fun postPublication(publicationObject: DomainPublication) {
        //database = Firebase.database.reference
        //database.child("publications").child("example").setValue(publicationObject);
        val publication = fetchPublications()
        //while (publication.count()<1) { }
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("publications")
        publicationObject.id = (listResult.count()+1).toString()
        myRef.child("pub"+publicationObject.id.toString()).setValue(getDomain(publicationObject))
    }
    private fun getDomain(publication: DomainPublication?): PublicationPublish {
        if(publication != null) {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            return PublicationPublish(publication.category, publication.title, publication.description, sdf.format(publication.date.time),publication.userName,publication.id)
        }
        return PublicationPublish("","","",Calendar.getInstance().toString(),"","0")
    }
    override fun findPublication(query:String) : Pair<Boolean, DomainPublication>{

        var publications = listResult
        var listDomains = publications.map {
                publication -> publication.toDomainPublication()
        }
        var index = 0
        if (listDomains.count()>1)
        {
            for (publication in listDomains)
            {
                if (publication.title == query)
                    return Pair(true,publication)
            }
        }
        return Pair(false,DomainPublication("","0","","", Calendar.getInstance(),"",0))

    }

}