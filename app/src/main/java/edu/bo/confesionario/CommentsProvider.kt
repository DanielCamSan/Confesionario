package edu.bo.confesionario

import edu.bo.confesionario.ListPublications.Companion.getDate
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CommentsProvider {
    companion object{

        val commentsList = listOf<Comment>(

            Comment("Justin1", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", getDate(2022,2,11)),
            Comment("Justin2", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", getDate(2022,2,11))
        )
    }
}