package edu.bo.framework

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.bo.data.IRemoteDataSource
import java.util.*
import edu.bo.domain.Publication

class DatabaseRef : IRemoteDataSource {
    fun getDate(year:Int,month:Int,day:Int) : Calendar
    {
        val date =  Calendar.getInstance()
        date.set(year, month, day)
        return date
    }
    private suspend fun getList(): MutableList<Publication> {

        //var listp = getPub()
        var listPublications = arrayListOf<Publication>()
        listPublications.add(
            Publication(
                "libros",
                "1",
                "Recogida de libros para caridad",
                "Se requiere libros que sean donados para crear una libreria de acceso publico.",
                getDate(2020,6,22),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "2",
                "Recogida de libros",
                "Se pide que los estudiantes del area de Ingenieria de la Siesta recoga sus libros.",
                getDate(2022,6,23),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "3",
                "Compra de libros",
                "Se vende libros para las personas interesadas venir planta baja en el edificio principal.",
                getDate(2020,7,22),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "4",
                "Libros logrando",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,7,23),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "5",
                "Libros prohibidos",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,7,23),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "6",
                "Libros 1",
                "Se ha perdido mi libro",
                getDate(2020,7,23),
                "Juan"
            )
        )
        listPublications.add(
            Publication(
                "libros",
                "7",
                "Libros 2",
                "Mi libro no aguantó hacer cuña a una mesa",
                getDate(2020,7,26),
                "Pedro"
            )
        )
        listPublications.add(
            Publication(
                "clases",
                "8",
                "Discretas",
                "No le entiendo al mgr",
                getDate(2020,7,30),
                "Dussan"
            )
        )
        listPublications.add(
            Publication(
                "clases",
                "9",
                "Inglés",
                "El Thomas",
                getDate(2020,8,1),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "fiestas",
                "10",
                "Bienvenida",
                "Los nuevios tienen que tener un bautizo",
                getDate(2020,8,9),
                "Dani"
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "11",
                "Soy gei",
                "lo reconozco, me gusta el wilsterman",
                getDate(2020,8,11),
                "Dussan"
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "12",
                "Me gusta la pizza con piña",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,8,14),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "13",
                "Sera posible esto we",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,8,16),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "14",
                "Mira we",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,8,18),
                "Tom"
            )
        )
        listPublications.add(
            Publication(
                "confesiones",
                "15",
                "Confesion aleatoria",
                "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
                getDate(2020,10,20),
                "Tom"
            )
        )
        return listPublications
    }
    private var listResult = arrayListOf<Publication>()
    private var database = FirebaseDatabase.getInstance().getReference().child("publications")
    constructor(){
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("ddddddd","ddddddaaaaaaaaaaaaaaaaqqqqqqqqqqqqqqqqwwwwwwwwwwwwwwwweeeeeeeeeeeeeee")
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (snap in dataSnapshot.children) {
                    var date = Calendar.getInstance()
                    date.set(2,2,2)
                    val publication = Publication(
                        snap.child("category").value.toString(),
                        snap.child("id").value.toString(),
                        snap.child("title").value.toString(),
                        snap.child("description").value.toString(),
                        date,
                        snap.child("idUser").value.toString()
                    )
                    listResult.add(publication)
                    Log.d("s1", snap.child("id").value.toString())
                    Log.d("s1", snap.child("title").value.toString())
                    Log.d("s1", snap.child("description").value.toString())
                    Log.d("s1", snap.child("date").value.toString())
                    Log.d("s1", snap.child("idUser").value.toString())

                    Log.d("erer",listResult.count().toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Error", "Fallo al recuperar las publicaciones.", error.toException())
            }
        })
    }
    override suspend fun getPublications(apiKey: String): List<Publication> {
        //listResult = getList()
        Log.d("count",listResult.count().toString())
        Log.d("ddddddd","si lo fue prro")
        return listResult
    }

}