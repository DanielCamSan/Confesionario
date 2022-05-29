package edu.bo.data

import edu.bo.domain.Publication
import java.util.*

class PublicationsRepository (val remoteDataSource: IRemoteDataSource) {

    suspend fun getPublications() = remoteDataSource.getPublications()
    fun getDate(year:Int,month:Int,day:Int) : Calendar
    {
        val date =  Calendar.getInstance()
        date.set(year, month, day)
        return date
    }
    suspend fun postPublication(publicationObject:Publication)
    {
        return remoteDataSource.postPublication(publicationObject)
    }

}