package edu.bo.ucb.framework
import edu.bo.ucb.data.IRemoteDataSource
import edu.bo.ucb.domain.Publication

class PublicationDataSource(val apiRest: RetrofitBuilder) : IRemoteDataSource {
    override suspend fun getPublications(apiKey: String): List<Publication> {
        val response = apiRest.apiService.listPublications(apiKey)
            .results.map {
                it.toDomainMovie()
            }
        return response
    }
}
