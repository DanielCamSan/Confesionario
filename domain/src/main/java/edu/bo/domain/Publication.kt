package edu.bo.domain

import java.util.*

data class Publication(val category: String?, var id: String?, val title: String?, val description: String?, val date: Calendar, val userName:String?, var numberOfCommentaries:Int)
//data class Publication(val category: String?, val number: String?, val title: String?, val description: String?, val date: Calendar, val userName:String?)//, val id:Int?)
