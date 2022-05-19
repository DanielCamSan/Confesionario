package edu.bo.data
import edu.bo.domain.Publication

interface ILocalDataSource {
    suspend fun getPublications(): List<Publication>
}