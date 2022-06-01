package edu.bo.data

import edu.bo.domain.Comment
import java.util.*

class CommentsRepository(val remoteDataSource: IFirebaseCommentsDataSource) {
    suspend fun getComments() = remoteDataSource.getComments()
    suspend fun  postComment(newComment: Comment) = remoteDataSource.postComment(newComment)
}