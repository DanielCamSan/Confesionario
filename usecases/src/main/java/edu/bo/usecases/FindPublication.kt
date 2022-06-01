package edu.bo.usecases

import edu.bo.data.PublicationsRepository
import edu.bo.domain.Publication

class FindPublication(val repository: PublicationsRepository) {
    fun invoke(query:String): Pair<Boolean,Publication> {
        return repository.findPublication(query)
    }
}