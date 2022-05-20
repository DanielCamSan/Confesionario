package edu.bo.domain
import java.util.*

data class Comment (
    val id: Int = 0,
    val username: String = "",
    val idUser: Int = 0,
    val idPublication: Int = 0,
    val commentBody: String = "",
    val commentDate: Calendar = Calendar.getInstance()
)