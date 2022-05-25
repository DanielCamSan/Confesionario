package edu.bo.data

import edu.bo.domain.Publication

interface IRemoteDataSource {
    suspend fun getPublications(): List<Publication>
    suspend fun postPublication(publicationObject: Publication)//:Publication
}