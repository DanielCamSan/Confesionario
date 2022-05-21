package edu.bo.usecases

import edu.bo.data.CommentsRepository
import edu.bo.domain.Comment

class PostComment (val repository: CommentsRepository) {
    suspend fun invoke(newComment: Comment) {
        return repository.postComment(newComment)
    }
}