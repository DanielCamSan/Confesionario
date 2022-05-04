package edu.bo.ucb.usecases

import edu.bo.ucb.data.PublicationsRepository
import edu.bo.ucb.domain.Publication

class GetPublications(val repository: PublicationsRepository) {
    suspend fun invoke(): List<Publication> {
        return repository.getPublications()
    }
}