package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

import io.ktor.server.application.*
import io.ktor.server.locations.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Locations)
        configureRouting()
    }.start(wait = true)
}
