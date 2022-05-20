package edu.bo.framework
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

import edu.bo.domain.Publication as DomainPublication
import edu.bo.framework.Publication as ServerPublication

fun ServerPublication.toDomainPublication(): DomainPublication {
    val newDate = Calendar.getInstance()
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    newDate.time = sdf.parse(date)
    return DomainPublication(category, number, title, description, newDate, userName)
}


