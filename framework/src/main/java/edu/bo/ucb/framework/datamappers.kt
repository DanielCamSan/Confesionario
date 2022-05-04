package edu.bo.ucb.framework
import retrofit2.http.GET
import retrofit2.http.Query

import edu.bo.ucb.domain.Publication as DomainPublication
import edu.bo.ucb.framework.Publication as ServerPublication

fun ServerPublication.toDomainMovie(): DomainPublication {
    return DomainPublication(category, number, title, description, date, userName)
}


