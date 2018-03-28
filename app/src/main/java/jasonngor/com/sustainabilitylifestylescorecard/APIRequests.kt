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

// ENUM CLASSES
enum class CommuteMethod {
    WALK, RUN, BIKE, DRIVE, CARPOOL, PUBLIC_TRANSIT
}
enum class FoodCategory {
    COOKED, FAST, RESTAURANT
}
enum class FoodUnit {
    POUNDS, OUNCES, KILOS, UNIT
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
        val timestamp: String, // Use ISO 8601 datestrings
        val location: String?
)


// CONTENT DATA CLASSES
abstract class Content {}

data class Food (
        val name: String,
        val quantity: Double? = null,
        val quantityUnits: FoodUnit? = null,
        val calories: Int,
        val category: FoodCategory,
        val mealTime: String
) : Content()

// TODO: Attachments
data class Journal (
        val title: String,
        val contents: String,
        val created: String? = null,
        val edited: String? = null
) : Content()

data class Commute (
        val method: CommuteMethod,
        val distance: Double,
        val departure: String,
        val arrival: String
) : Content()


// REQUEST DATA CLASSES
abstract class AuthenticatedRequest { abstract val token: Token }

data class PostRequest<out T: Content> (
        val content: T,
        override val token: Token,
        val metadata: MetaData
) : AuthenticatedRequest()

data class GetRequest( // The targeted API endpoint is the content parameterization
        val date: String,
        override val token: Token
) : AuthenticatedRequest()


// RESPONSE DATA CLASSES
open class Response (val message: String, val result: Boolean) // kotlin classes default to final

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
    @POST("food/new")
    fun postFood(@Body request: PostRequest<Food>): Call<Response>

    @Headers("Content-Type: application/json")
    @POST("journal/new")
    fun postJournal(@Body request: PostRequest<Journal>): Call<Response>

    @Headers("Content-Type: application/json")
    @POST("commute/new")
    fun postCommute(@Body request: PostRequest<Commute>): Call<Response>


    // Content GET requests
    @Headers("Content-Type: application/json")
    @POST("food")
    fun getFoods(@Body request: GetRequest): Call<ListResponse<Food>>

    @Headers("Content-Type: application/json")
    @POST("journal")
    fun getJournals(@Body request: GetRequest): Call<ListResponse<Journal>>

    @Headers("Content-Type: application/json")
    @POST("commute")
    fun getCommutes(@Body request: GetRequest): Call<ListResponse<Commute>>
}
