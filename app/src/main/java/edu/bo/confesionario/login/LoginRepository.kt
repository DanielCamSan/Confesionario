package edu.bo.confesionario.login

class LoginRepository( ) {
    fun login(userName: String, password: String) : Response<String> {
        if( userName == "usuarioclave") {
            return Response(0, "success")
        } else {
            return Response(-1, message = "error")
        }
    }
}

data class Response<T>(
    val code: Int = 0,
    val message: String = "",
    val result: T? = null
)
