package edu.bo.domain
import java.util.*

data class Comment (
    val id: String = "",
    val username: String? = "",
    val idUser: String? = "",
    val idPublication: Int? = 0,
    val commentBody: String = "",
    val commentDate: Calendar = Calendar.getInstance()
)