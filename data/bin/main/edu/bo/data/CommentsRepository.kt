package edu.bo.data

import edu.bo.domain.Comment
import java.util.*

class CommentsRepository(val remoteDataSource: IFirebaseCommentsDataSource, val apiKey: String) {
    suspend fun getComments() = remoteDataSource.getComments(apiKey)

}