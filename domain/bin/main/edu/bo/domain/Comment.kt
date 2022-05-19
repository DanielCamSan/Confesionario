package edu.bo.domain
import java.util.*

data class Comment (val username: String = "", val commentBody: String = "", val commentDate: Calendar = Calendar.getInstance())