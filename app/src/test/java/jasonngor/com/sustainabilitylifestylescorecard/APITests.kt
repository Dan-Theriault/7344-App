package jasonngor.com.sustainabilitylifestylescorecard

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Created by dtheriault3 on 3/27/18.
 */
class APITests {
    val wrap: APIWrapper = APIWrapper()
    lateinit var token: Token
    val today: String = "2018-04-05"

    @Test
    fun statusTest() {
        val request = wrap.status()
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(true, result?.result)
    }

    @Test
    fun executeHelperTest() {
        val request = wrap.status()
        val result = wrap.executeRequest(request)

        println(result?.message)

        assertEquals(true, result?.result)
    }

    @Test
    fun registrationTest() {
        val request = wrap.register("tester86","password")
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(false, result?.result)
    }

    @Test
    @Before
    fun SuccessfulLoginTest() {
        val request = wrap.login("tester86","password")
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
        val request = wrap.login("tester86","notmypassword")
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(false, result?.result)
        assertNull(result!!.token)
    }

    @Test
    fun PostCommuteTest() {
        val content = Commute(
                CommuteMethod.BIKE,
                10.0,
                "2018-03-27T16:09:03",
                "2018-03-27T16:58:12"
        )
        val request = wrap.postCommute(content, token)
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(true, result?.result)
    }

    @Test
    fun PostJournalTest() {
        val content = Journal(
            "Writing Tests in Kotlin", "Wow, this is pretty cool!"
        )
        val request = wrap.postJournal(content, token)
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(true, result?.result)
    }

    @Test
    fun PostFoodTest() {
        val content = Food(
                "Bacon Cheeseburger",
                1.0 / 3,
                FoodUnit.POUNDS,
                900,
                FoodCategory.RESTAURANT,
                "2018-03-27T17:20:08"
        )
        val request = wrap.postFood(content, token)
        val response = request.execute()
        val result = response.body()

        println(result?.message)
        assertEquals(true, result?.result)
    }

    @Test
    fun GetJournalTest() {
        val request = wrap.getJournals(token, "2018-04-05")
        val response = request.execute()
        val result = response.body()

        println(result?.list)
        assertEquals(true, result?.result)
        assertNotEquals(0, result?.list?.size)
    }

    @Test
    fun GetCommuteTest() {
        val request = wrap.getCommutes(token, "2018-03-27")
        val response = request.execute()
        val result = response.body()

        println(result?.list)
        assertEquals(true, result?.result)
        assertNotEquals(0, result?.list?.size)
    }

    @Test
    fun GetFoodTest() {
        val request = wrap.getFoods(token, "2018-03-27")
        val response = request.execute()
        val result = response.body()

        println(result?.list)
        assertEquals(true, result?.result)
        assertNotEquals(0, result?.list?.size)
    }


    @Test
    fun GetWaterTest() {
        val request = wrap.getWater(token, today)
        val response = request.execute()
        val result = response.body()

        println(result?.content)
        assertEquals(true, result?.result)
        assertNotNull(result?.content)
    }

    @Test
    fun GetShowerTest() {
        val request = wrap.getShowers(token, today)
        val response = request.execute()
        val result = response.body()

        println(result?.content)
        assertEquals(true, result?.result)
        assertNotNull(result?.content)
    }

    @Test
    fun GetEntertainmentTest() {
        val request = wrap.getEntertainment(token, today)
        val response = request.execute()
        val result = response.body()

        println(result?.content)
        assertEquals(true, result?.result)
        assertNotNull(result?.content)
    }

    @Test
    fun GetHealthTest() {
        val request = wrap.getHealth(token, today)
        val response = request.execute()
        val result = response.body()

        println(result?.content)
        assertEquals(true, result?.result)
        assertNotNull(result?.content)
    }
}