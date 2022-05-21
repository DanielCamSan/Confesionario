package edu.bo.data

import edu.bo.domain.Publication
import java.util.*

class PublicationsRepository (val remoteDataSource: IRemoteDataSource) {

    suspend fun getPublications() = remoteDataSource.getPublications()
    //suspend fun getPublications() = remoteDataSource.getPublications(apiKey)

}