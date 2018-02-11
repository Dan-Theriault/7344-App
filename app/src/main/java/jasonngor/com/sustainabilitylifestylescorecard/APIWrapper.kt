package jasonngor.com.sustainabilitylifestylescorecard

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by dtheriault3 on 2/11/18.
 */

class APIWrapper {
    private val api: APIRequests

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://34.230.175.34:8000/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        api = retrofit.create(APIRequests::class.java)
    }

    fun status(): Call<Response> {
        return api.getStatus()
    }

    fun login(username: String, password: String): Call<TokenResponse> {
        val user: UserData = UserData(username, password)
        return api.postLogin(user)
    }

    fun register(username: String, password: String): Call<Response> {
        val user: UserData = UserData(username, password)
        return api.postRegister(user)
    }
}
