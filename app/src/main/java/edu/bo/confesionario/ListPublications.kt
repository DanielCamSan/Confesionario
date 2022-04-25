package edu.bo.confesionario

import java.time.LocalDate
import java.util.*

class ListPublications {
    companion object {
        @JvmStatic
        fun getList(): List<Publication> {
            var listPublications = arrayListOf<Publication>()
            listPublications.add(
                Publication(
                    "libros",
                    "1",
                    "Wea1",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "libros",
                    "2",
                    "Wea1",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "libros",
                    "3",
                    "Wea1",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "libros",
                    "4",
                    "Wea1",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "libros",
                    "5",
                    "Wea1",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "libros",
                    "6",
                    "Libros 1",
                    "Se ha perdido mi libro",
                    LocalDate.parse("2022-03-03"),
                    "Juan"
                )
            )
            listPublications.add(
                Publication(
                    "libros",
                    "7",
                    "Libros 2",
                    "Mi libro no aguantó hacer cuña a una mesa",
                    LocalDate.parse("2022-03-03"),
                    "Pedro"
                )
            )
            listPublications.add(
                Publication(
                    "clases",
                    "8",
                    "Discretas",
                    "No le entiendo al mgr",
                    LocalDate.parse("2022-03-03"),
                    "Dussan"
                )
            )
            listPublications.add(
                Publication(
                    "clases",
                    "9",
                    "Inglés",
                    "El Thomas",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "fiestas",
                    "10",
                    "Bienvenida",
                    "Los nuevios tienen que tener un bautizo",
                    LocalDate.parse("2022-03-03"),
                    "Dani"
                )
            )
            listPublications.add(
                Publication(
                    "confesiones",
                    "11",
                    "Soy gei",
                    "lo reconozco, me gusta el wilsterman",
                    LocalDate.parse("2022-03-03"),
                    "Dussan"
                )
            )
            listPublications.add(
                Publication(
                    "confesiones",
                    "12",
                    "Wea1",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "confesiones",
                    "13",
                    "Sera posible esto we",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "confesiones",
                    "14",
                    "Mira we",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            listPublications.add(
                Publication(
                    "confesiones",
                    "15",
                    "Wea1",
                    "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                    LocalDate.parse("2022-03-03"),
                    "Tom"
                )
            )
            return listPublications
        }
    }
}
