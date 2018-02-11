package jasonngor.com.sustainabilitylifestylescorecard

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by dtheriault3 on 2/11/18.
 */

// Data classes cannot be superclasses
// These abstract classes are necessary for multiple data classes to share a type
abstract class Response {
    abstract val message: String
    abstract val result: Boolean
}
abstract class AuthenticatedRequest {
    abstract val token: Token
}

// GENERAL DATA CLASSES
data class Token (
        val hash: String,
        val email: String,
        val expiry: String
)
data class UserData(val username: String, val password: String)

// RESPONSE DATA CLASSES
data class TokenResponse (
        val token: Token,
        override val message: String,
        override val result: Boolean
) : Response()


interface APIRequests {
    @Headers("Content-Type: application/json")
    @GET("/status")
    fun getStatus(): Call<Response>

    @Headers("Content-Type: application/json")
    @POST("/register")
    fun postRegister(@Body user: UserData)
            : Call<Response>

    @Headers("Content-Type: application/json")
    @POST("/login")
    fun postLogin(@Body user: UserData)
            : Call<TokenResponse>
}
