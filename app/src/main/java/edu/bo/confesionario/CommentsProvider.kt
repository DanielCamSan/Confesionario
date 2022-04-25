package edu.bo.confesionario

import java.time.LocalDate

class CommentsProvider {
    companion object{
        val commentsList = listOf<Comment>(
            Comment("Justin1", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", LocalDate.parse("2022-02-11")),
            Comment("Justin2", "El docente Pepe solo lee las diapositivas y no asiste puntualmente a clases", LocalDate.parse("2022-02-11"))
        )
    }
}