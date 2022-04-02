package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/hello/{name}") {
            val name = call.parameters["name"]
            call.respondText("Hello ${name}")
        }
        authenticate {
            get("/authenticated") {
                val user = call.authentication.principal<UserIdPrincipal>()
                call.respondText("authenticated id=${user?.name}")
            }
        }

        greetingRoute()
        userRoute()
        bookRoute()
    }
}

fun Routing.greetingRoute() {
    route("greeting") {
        get("/hello") {
            call.respondText("Hello Ktor!")
        }
    }
}

class UserLocation {
    @Location("/user/{id}")
    data class GetUserLocation(val id: Long)

    @Location("/detail/{id}")
    data class GetDetailLocation(val id: Long)
}

fun Routing.userRoute() {
    get<UserLocation.GetUserLocation> { param ->
        val id = param.id
        call.respondText("id=${id}")
    }
}

fun Routing.bookRoute() {
    route("/book") {
        @Location("/detail/{bookId}")
        data class BookLocation(val bookId: Long)

        get<BookLocation> { request ->
            val response = BookResponse(request.bookId, "kotlin-book", "kotlin-man")
            call.respond(response)
        }

        post("/register") {
            val request = call.receive<RegisterRequest>()
            call.respondText("registered.id=${request.id} title=${request.title} author=${request.author}")
        }
    }
}

data class BookResponse(
    val id: Long,
    val title: String,
    val author: String
)

data class RegisterRequest(
    val id: Long,
    val title: String,
    val author: String
)
