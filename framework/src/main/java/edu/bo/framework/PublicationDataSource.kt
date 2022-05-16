package edu.bo.framework
import edu.bo.data.IRemoteDataSource
import edu.bo.domain.Publication

class PublicationDataSource(val apiRest: RetrofitBuilder) : IRemoteDataSource {
    override suspend fun getPublications(apiKey: String): List<Publication> {
        val response = apiRest.apiService.listPublications(apiKey)
            .results.map {
                it.toDomainMovie()
            }
        return response
    }
}
