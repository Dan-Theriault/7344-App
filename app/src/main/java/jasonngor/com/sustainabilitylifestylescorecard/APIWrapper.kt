package jasonngor.com.sustainabilitylifestylescorecard

import android.location.Location
import android.os.AsyncTask
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

/**
 * Created by dtheriault3 on 2/11/18.
 */

class APIWrapper {
    val api: APIRequests
//    private val locationClient: FusedLocationProviderClient

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://ess.dtheriault.com/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        api = retrofit.create(APIRequests::class.java)
//        locationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    fun executeRequest(callable: Call<Response>): Response? {
        val obj = object : AsyncTask<Void, Void, Response?>() {
            override fun doInBackground(vararg p: Void?): Response? {
                val response = callable.execute()
                return response.body()
            }
        }.execute()
        return obj?.get()
    }

    // THESE FUNCTIONS DO NOT, THEMSELVES, EXECUTE API REQUESTS
    // They return Callable request objects.
    // I provided `executeRequest`, above, to asynchronously run these generated requests.
    // ex: to execute status, api.executeRequest(api.status())
    fun status(): Call<Response> {
        return api.getStatus()
    }

    fun login(email: String, password: String): Call<TokenResponse> {
        val user: UserData = UserData(email, password)
        return api.postLogin(user)
    }

    fun register(email: String, password: String): Call<TokenResponse> {
        val user: UserData = UserData(email, password)
        return api.postRegister(user)
    }

    // token and all content classes are specified in APIRequests.kt
    fun postJournal(content: Journal, token: Token): Call<Response> {
        val meta = MetaData(GregorianCalendar(TimeZone.getTimeZone("UTC")).time, null )
        val body = PostRequest<Journal>(content, token, meta)
        return api.postJournal(body)
    }

    fun postCommute(content: Commute, token: Token): Call<Response> {
        val meta = MetaData(GregorianCalendar(TimeZone.getTimeZone("UTC")).time, null )
        val body = PostRequest<Commute>(content, token, meta)
        return api.postCommute(body)
    }

    fun postFood(content: Food, token: Token): Call<Response> {
        val meta = MetaData(GregorianCalendar(TimeZone.getTimeZone("UTC")).time, null )
        val body = PostRequest<Food>(content, token, meta)
        return api.postFood(body)
    }

    fun getJournals(token: Token, date: Date): Call<Response> {
        val body = GetRequest(date, token)
        return  api.getJournals(body)
    }
    fun getCommutes(token: Token, date: Date): Call<Response> {
        val body = GetRequest(date, token)
        return  api.getCommutes(body)
    }
    fun getFoods(token: Token, date: Date): Call<Response> {
        val body = GetRequest(date, token)
        return  api.getFoods(body)
    }
}
