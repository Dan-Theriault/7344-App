package jasonngor.com.sustainabilitylifestylescorecard

import android.location.Location
import android.telecom.CallScreeningService
import retrofit2.Call
import retrofit2.http.*
import java.lang.reflect.Type
import java.util.*

/**
 * Created by dtheriault3 on 2/11/18.
 */


// Data classes cannot be superclasses
// These abstract classes are necessary for multiple data classes to share a type





// ENUM CLASSES
enum class CommuteMethod {
    WALK, RUN, BIKE, DRIVE, CARPOOL, PUBLIC_TRANSIT
}
enum class FoodCategory {
    COOKED, FAST, RESTAURANT
}


// BASIC DATA CLASSES
data class Token (
        val hash: String,
        val email: String,
        val expiry: String
)
data class UserData (
        val email: String,
        val password: String
)
data class MetaData (
        val datetime: Date,
        val location: Location?
)


// CONTENT DATA CLASSES
abstract class Content {}

data class Food (
        val name: String,
        val quantity: Float,
        val calories: Int,
        val category: FoodCategory
) : Content()

data class Journal (
        val title: String,
        val contents: Nothing, // TODO: File Handling
        val attachments: Nothing?
) : Content()

data class Commute (
        val method: CommuteMethod,
        val distance: Float,
        val departure: Date,
        val arrival: Date
) : Content()


// REQUEST DATA CLASSES
abstract class AuthenticatedRequest { abstract val token: Token }

data class PostRequest<out T: Content> (
        val content: T,
        override val token: Token,
        val metaData: MetaData
) : AuthenticatedRequest()

data class GetRequest( // The targeted API endpoint is the content parameterization
        val date: Date,
        override val token: Token
) : AuthenticatedRequest()


// RESPONSE DATA CLASSES
open class Response (val message: String, val result: Boolean)

class TokenResponse (
        val token: Token,
        message: String,
        result: Boolean
) : Response(message, result)

class ListResponse<T> (
        val list: List<T>,
        message: String,
        result: Boolean
) : Response(message, result)


interface APIRequests {
    @Headers("Content-Type: application/json")
    @GET("status")
    fun getStatus(): Call<Response>


    // UserData requests
    @Headers("Content-Type: application/json")
    @POST("register")
    fun postRegister(@Body user: UserData): Call<TokenResponse>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun postLogin(@Body user: UserData): Call<TokenResponse>


    // Content POST requests
    @Headers("Content-Type: application/json")
    @POST("food")
    fun postFood(@Body request: PostRequest<Food>): Call<Response>

    @Headers("Content-Type: application/json")
    @POST("journal")
    fun postJournal(@Body request: PostRequest<Journal>): Call<Response>

    @Headers("Content-Type: application/json")
    @POST("commute")
    fun postCommute(@Body request: PostRequest<Commute>): Call<Response>


    // Content GET requests
    @Headers("Content-Type: application/json")
    @GET("food")
    fun getFoods(@Body request: GetRequest): Call<Response>

    @Headers("Content-Type: application/json")
    @GET("journal")
    fun getJournals(@Body request: GetRequest): Call<Response>

    @Headers("Content-Type: application/json")
    @GET("commute")
    fun getCommutes(@Body request: GetRequest): Call<Response>
}
