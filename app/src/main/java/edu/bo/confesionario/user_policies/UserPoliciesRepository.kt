package edu.bo.confesionario.user_policies

class UserPoliciesRepository() {
    fun acceptPolicies(choose:Boolean) : Response<String> {
        if( choose) {
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
