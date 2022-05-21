package edu.bo.usecases

import edu.bo.data.PublicationsRepository
import edu.bo.domain.Publication

class PostPublication(val repository: PublicationsRepository) {
    suspend fun invoke(publicationObject: Publication):Publication{
        repository.postPublication(publicationObject)
        return  publicationObject;
    }
}