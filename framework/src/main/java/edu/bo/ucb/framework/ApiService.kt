package edu.bo.ucb.framework

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?short_by=popularity.desc")
    suspend fun listPublications(@Query("api_key") apiKey: String) : MovieResponse
}

class MovieResponse(val page: Int, val results: List<Publication>)


