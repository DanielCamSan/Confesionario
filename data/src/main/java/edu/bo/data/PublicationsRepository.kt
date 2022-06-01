package edu.bo.data

import edu.bo.domain.Publication
import java.util.*

class PublicationsRepository (val remoteDataSource: IRemoteDataSource) {

    suspend fun getPublications() = remoteDataSource.getPublications()

    suspend fun postPublication(publicationObject:Publication)
    {
        return remoteDataSource.postPublication(publicationObject)
    }
    fun findPublication(query:String) : Pair<Boolean, Publication>
    {
        return remoteDataSource.findPublication(query)
    }

}