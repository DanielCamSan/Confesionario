package edu.bo.framework
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.bo.data.IRemoteDataSource
import edu.bo.domain.Publication



class PublicationDataSource(val apiRest: RetrofitBuilder) : IRemoteDataSource {
    private lateinit var database: DatabaseReference
    override suspend fun getPublications(): List<Publication> {
        val response = apiRest.apiService.listPublications()
            .results.map {
                it.toDomainPublication()
            }
        return response
    }

    override suspend fun postPublication(
        apiKey: String,
        publicationObject: Publication
    ) {
        database = Firebase.database.reference
        database.child("publications").child("example").setValue(publicationObject);
//        val response=apiRest.apiService;
        print("llegue?");
    }
}
