package edu.bo.confesionario.user_policies

class UserPoliciesRepository () {
    fun acceptRules(state: Boolean ) : Response<String> {
        if(state) {
            return Response(0, "Reglas acceptadas")
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
