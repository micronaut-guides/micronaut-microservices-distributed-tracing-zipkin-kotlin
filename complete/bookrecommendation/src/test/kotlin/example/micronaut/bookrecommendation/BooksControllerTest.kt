package example.micronaut.bookrecommendation

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxStreamingHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class BooksControllerTest {
    @Inject
    @field:Client("/")
    lateinit var client: RxStreamingHttpClient

    @Test
    fun testRetrieveBooks() {
        val books = client.jsonStream(HttpRequest.GET<Any>("/books"), BookRecommendation::class.java)
        Assertions.assertEquals(books.toList().blockingGet().size, 1)
        Assertions.assertEquals(books.toList().blockingGet()[0].name, "Building Microservices")
    }
}