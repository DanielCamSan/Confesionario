package edu.bo.usecases

import edu.bo.data.PublicationsRepository
import edu.bo.domain.Publication

class GetPublications(val repository: PublicationsRepository) {
    suspend fun invoke(): List<Publication> {
        return repository.getPublications()
    }
}