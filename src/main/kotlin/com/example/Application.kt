package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.*
import io.ktor.serialization.jackson.*
import io.ktor.server.auth.*
import java.nio.file.attribute.UserPrincipal

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Locations)
        install(ContentNegotiation) {
            jackson()
        }
        install(Authentication) {
            basic {
                validate { credentials ->
                    if (credentials.name == "user" && credentials.password == "password" ) {
                        UserIdPrincipal(credentials.name)
                    } else {
                        null
                    }
                }
            }
        }
        configureRouting()
    }.start(wait = true)
}
