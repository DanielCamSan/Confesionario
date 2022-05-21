package edu.bo.data

import edu.bo.domain.Publication
import java.util.*

class PublicationsRepository (val remoteDataSource: IRemoteDataSource, val apiKey: String) {

    suspend fun getPublications() = remoteDataSource.getPublications(apiKey)
    //suspend fun getPublications() = remoteDataSource.getPublications(apiKey)
    private suspend fun getPub() = remoteDataSource.getPublications(apiKey)

}