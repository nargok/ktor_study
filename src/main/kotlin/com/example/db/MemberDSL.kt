package com.example.db

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect(
        "jdbc:mysql://127.0.0.1:3306/exposed_example",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "docker",
        password = "docker"
    )

    transaction {
        addLogger(StdOutSqlLogger)

        val id = Member.insert {
            it[name] = "kotlin"
        } get Member.id
        println("Inserted id: $id")

        val member = Member.select { Member.id eq id }.single()
        println("id: ${member[Member.id]}")
        println("name: ${member[Member.name]}")
    }
}


object Member : Table("member") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 32)
}