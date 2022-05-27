package edu.bo.usecases;

import edu.bo.data.CommentsRepository;
import edu.bo.domain.Comment;

class GetAllComments(val repository : CommentsRepository) {
    suspend fun invoke(): List<Comment?> {
        return repository.getAllComments()
    }
}
