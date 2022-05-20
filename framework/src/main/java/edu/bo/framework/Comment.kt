package edu.bo.framework

import java.util.*

data class Comment (
    val id: Int = 0,
    val username: String = "",
    val idUser: Int = 0,
    val idPublication: Int = 0,
    val commentBody: String = "",
    val commentDate: String = ""
)