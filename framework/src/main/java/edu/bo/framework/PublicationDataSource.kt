package edu.bo.framework
import edu.bo.data.IRemoteDataSource
import edu.bo.domain.Publication

class PublicationDataSource(val apiRest: RetrofitBuilder) : IRemoteDataSource {
    override suspend fun getPublications(): List<Publication> {
        val response = apiRest.apiService.listPublications()
            .results.map {
                it.toDomainPublication()
            }
        return response
    }
}
