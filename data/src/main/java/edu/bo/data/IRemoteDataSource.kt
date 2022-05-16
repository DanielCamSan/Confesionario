package edu.bo.data

import edu.bo.domain.Publication

interface IRemoteDataSource {
    suspend fun getPublications( apiKey: String): List<Publication>
}