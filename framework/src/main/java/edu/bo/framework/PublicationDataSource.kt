package edu.bo.framework
import android.widget.Toast
//import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import edu.bo.data.IRemoteDataSource
import edu.bo.domain.Publication
import edu.bo.framework.PublicationPublish as FPublication
import edu.bo.domain.Publication as PublicationDTO




class PublicationDataSource(val apiRest: RetrofitBuilder) : IRemoteDataSource {
    private lateinit var database: DatabaseReference
    override suspend fun getPublications(): List<Publication> {
        val response = apiRest.apiService.listPublications()
            .results.map {
                it.toDomainPublication()
            }
        return response
    }
    override suspend fun postPublication(publicationObject: Publication) {
        //database = Firebase.database.reference
        //database.child("publications").child("example").setValue(publicationObject);
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("publications")
        myRef.child(publicationObject.id.toString()).setValue(getDomain(publicationObject))
    }
    private fun getDomain(publication: PublicationDTO?): FPublication{
        if(publication != null) {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            return FPublication(publication.category, publication.number, publication.title, publication.description, sdf.format(publication.date.time),publication.userName, publication.id)
        }
        return FPublication("","","","",Calendar.getInstance().toString(),"",0)
    }
}
