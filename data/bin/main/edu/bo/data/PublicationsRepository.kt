package edu.bo.data

import edu.bo.domain.Publication
import java.util.*

class PublicationsRepository (val remoteDataSource: IRemoteDataSource) {

    suspend fun getPublications() = remoteDataSource.getPublications()
    //suspend fun getPublications() = remoteDataSource.getPublications(apiKey)
    //suspend fun postPublication() = postPublication(publicationObject)
    fun postPublication(publicationObject:Publication) : Publication
    {
        //usar el remote data source que llama al framework
        //framework si o si lo de firebase
        //
        return publicationObject
    }

}