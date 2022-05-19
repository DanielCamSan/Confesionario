package edu.bo.data

import edu.bo.domain.Comment

interface IFirebaseCommentsDataSource {
    suspend fun getComments(): List<Comment?>
}