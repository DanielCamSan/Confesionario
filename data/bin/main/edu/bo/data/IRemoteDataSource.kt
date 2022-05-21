package edu.bo.data

import edu.bo.domain.Publication

interface IRemoteDataSource {
<<<<<<< HEAD
    suspend fun getPublications(): List<Publication>
    suspend fun postPublication(publicationObject: Publication)//:Publication
=======
    suspend fun getPublications( apiKey: String): List<Publication>
    suspend fun postPublication(publicationObject: Publication)
>>>>>>> b1ad7e4 (publication)
}