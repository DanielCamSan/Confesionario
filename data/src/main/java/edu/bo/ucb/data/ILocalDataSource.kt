package edu.bo.ucb.data
import edu.bo.ucb.domain.Publication

interface ILocalDataSource {
    suspend fun getPublications(): List<Publication>
}