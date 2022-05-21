package edu.bo.framework

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("publications")
    suspend fun listPublications() : MovieResponse
}

class MovieResponse(val page: Int, val results: List<Publication>)


