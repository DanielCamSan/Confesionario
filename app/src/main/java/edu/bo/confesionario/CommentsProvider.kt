package edu.bo.confesionario

import java.util.*

class CommentsProvider {
    companion object{

        val commentsList = listOf<Comment>(

            Comment("Justin1", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", getDate(2022,2,11)),
            Comment("Justin2", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", getDate(2022,2,11))
        )

        private fun getDate(year:Int,month:Int,day:Int): Calendar {
            val date =  Calendar.getInstance()
            date.set(year, month, day)
            return date
        }
    }
}