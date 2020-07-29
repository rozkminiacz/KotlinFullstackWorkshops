package tech.michalik

import io.kotlintest.assertSoftly
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication

class ApplicationSpecification : StringSpec({
    val testParams = mapOf(
        "/json" to "application/json; charset=UTF-8",
        "/" to "text/plain; charset=UTF-8",
        "/html" to "text/html; charset=UTF-8"
    )

    testParams.forEach { (endpoint, expectedContentType) ->
        "GET on $endpoint should return success and $expectedContentType"{
            withTestApplication({ module() }) {
                handleRequest(HttpMethod.Get, endpoint).apply {
                    assertSoftly {
                        response.status() shouldBe HttpStatusCode.OK
                        response.headers["Content-Type"] shouldBe expectedContentType
                    }
                }
            }
        }
    }
})
