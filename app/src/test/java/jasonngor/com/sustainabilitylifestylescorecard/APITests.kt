package jasonngor.com.sustainabilitylifestylescorecard

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

/**
 * Created by dtheriault3 on 3/27/18.
 */
class APITests {
    val wrap: APIWrapper = APIWrapper()
    lateinit var token: Token

    @Test
    fun statusTest() {
        val request = wrap.api.getStatus()
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(true, result?.result)
    }

    @Test
    fun executeHelperTest() {
        val request = wrap.api.getStatus()
        val result = wrap.executeRequest(request)


        println(result?.message)

        assertEquals(true, result?.result)
    }

    @Test
    fun registrationTest() {
        val request = wrap.api.postRegister(UserData("tester86","password"))
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(false, result?.result)
    }

    @Test
    fun SuccessfulLoginTest() {
        val request = wrap.api.postLogin(UserData("tester86","password"))
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(true, result?.result)
        token = (result as TokenResponse).token
        assertEquals("tester86", token.email)
        assertNotNull(token.hash)
        assertNotNull(token.expiry)
        println(token)
    }

    @Test
    fun BadPasswordLoginTest() {
        val request = wrap.api.postLogin(UserData("tester86","notmypassword"))
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(false, result?.result)
    }
}