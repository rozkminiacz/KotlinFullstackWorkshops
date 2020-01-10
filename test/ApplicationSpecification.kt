package tech.michalik

import io.kotlintest.specs.StringSpec
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.assertEquals

class ApplicationSpecification : StringSpec({
    "root should return plain text"{
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
                assertEquals("text/plain; charset=UTF-8", response.headers["Content-Type"])
            }
        }
    }
})
