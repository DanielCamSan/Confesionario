package edu.bo.data

import edu.bo.domain.Publication
import java.util.*

class PublicationsRepository (val remoteDataSource: IRemoteDataSource, val apiKey: String) {
    suspend fun getPublications() = getList()
        //remoteDataSource.getPublications(apiKey)
    fun getDate(year:Int,month:Int,day:Int) : Calendar
    {
        val date =  Calendar.getInstance()
        date.set(year, month, day)
        return date
    }
    fun getList(): List<Publication> {
        var listPublications = arrayListOf<Publication>()
        listPublications.add(
            Publication(
                "libros",
                "1",
                "Recogida de libros para caridad",
                "Se requiere libros que sean donados para crear una libreria de acceso publico.",
                getDate(2020,6,22),
                "Tom",
                1
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "2",
                "Recogida de libros",
                "Se pide que los estudiantes del area de Ingenieria de la Siesta recoga sus libros.",
                getDate(2022,6,23),
                "Tom",
                2
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "3",
                "Compra de libros",
                "Se vende libros para las personas interesadas venir planta baja en el edificio principal.",
                getDate(2020,7,22),
                "Tom",
                3
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "4",
                "Libros logrando",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,7,23),
                "Tom",
                4
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "5",
                "Libros prohibidos",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,7,23),
                "Tom",
                5
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "6",
                "Libros 1",
                "Se ha perdido mi libro",
                getDate(2020,7,23),
                "Juan",
                6
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "7",
                "Libros 2",
                "Mi libro no aguantó hacer cuña a una mesa",
                getDate(2020,7,26),
                "Pedro",
                7
            )
        )
        listPublications.add(
            Publication(
                "clases",
                "8",
                "Discretas",
                "No le entiendo al mgr",
                getDate(2020,7,30),
                "Dussan",
                8
            )
        )
        listPublications.add(
            Publication(
                "clases",
                "9",
                "Inglés",
                "El Thomas",
                getDate(2020,8,1),
                "Tom",
                9
            )
        )
        listPublications.add(
            Publication(
                "fiestas",
                "10",
                "Bienvenida",
                "Los nuevios tienen que tener un bautizo",
                getDate(2020,8,9),
                "Dani",
                10
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "11",
                "Soy gei",
                "lo reconozco, me gusta el wilsterman",
                getDate(2020,8,11),
                "Dussan",
                11
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "12",
                "Me gusta la pizza con piña",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,8,14),
                "Tom",
                12
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "13",
                "Sera posible esto we",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,8,16),
                "Tom",
                13
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "14",
                "Mira we",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,8,18),
                "Tom",
                14
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "15",
                "Confesion aleatoria",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,10,20),
                "Tom",
                15
            )
        )
        return listPublications
    }
}