package com.example.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
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

        val member = MemberEntity.new {
            name = "KotlinFromDAO"
        }
        println("Inserted id: ${member.id}")

        MemberEntity.findById(member.id)?.let {
            println("id: ${it.id}")
            println("name: ${it.name}")
        }
    }
}

object MemberTable : IntIdTable("member") {
    val name = varchar("name", 32)
}

class MemberEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemberEntity>(MemberTable)

    var name by MemberTable.name
}