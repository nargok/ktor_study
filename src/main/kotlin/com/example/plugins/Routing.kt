package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
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
        greetingRoute()
        userRoute()
    }
    routing {
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
