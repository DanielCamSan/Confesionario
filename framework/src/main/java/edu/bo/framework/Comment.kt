package edu.bo.framework

import java.util.*

data class Comment (
    val id: String = "",
    val username: String? = "",
    val idUser: String? = "",
    val idPublication: Int? = 0,
    val commentBody: String = "",
    val commentDate: String = ""
)