package edu.bo.ucb.data

import edu.bo.ucb.domain.Publication

interface IRemoteDataSource {
    suspend fun getPublications( apiKey: String): List<Publication>
}