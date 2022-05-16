package edu.bo.framework
import retrofit2.http.GET
import retrofit2.http.Query

import edu.bo.domain.Publication as DomainPublication
import edu.bo.framework.Publication as ServerPublication

fun ServerPublication.toDomainMovie(): DomainPublication {
    return DomainPublication(category, number, title, description, date, userName)
}


