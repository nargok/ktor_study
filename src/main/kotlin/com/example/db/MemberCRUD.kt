package com.example.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun createSessionFactory() {
    Database.connect(
        "jdbc:mysql://127.0.0.1:3306/exposed_example",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "docker",
        password = "docker"
    )
}

data class MemberModel(val id: Int, val name: String) {
    constructor(entity: MemberEntity) : this(entity.id.value, entity.name)
}

fun getMemberList() {
    createSessionFactory()
    transaction {
        val list = MemberEntity.all().map { MemberModel(it) }
        list.forEach {
            println(it)
        }
    }
}

fun getMember(memberId: Int) {
    createSessionFactory()
    transaction {
        val entity = MemberEntity.findById(memberId)
        entity?.let { println(MemberModel(it)) }
    }
}

fun getMember(name: String) {
    createSessionFactory()
    transaction {
        val entity = MemberEntity.find { MemberTable.name eq name }.map { MemberModel(it) }
        entity?.let { println(it) }
    }
}

fun registerMember(memberName: String) {
    createSessionFactory()
    transaction {
        val entity = MemberEntity.new {
            name = memberName
        }
        println(MemberModel(entity))
    }
}

fun updateMember(memberId: Int, newMemberName: String) {
    createSessionFactory()
    transaction {
        val entity = MemberEntity.findById(memberId)
        entity?.let {
            it.name = newMemberName
            println(MemberModel(it))
        }
    }
}

fun deleteMember(memberId: Int) {
    createSessionFactory()
    transaction {
        val entity = MemberEntity.findById(memberId)
        entity?.let {
            it.delete()
        }
    }
}


fun main() {
//    getMemberList()
//    getMember(2)
//    getMember("kotlin")
//    registerMember("hachiro")
//    updateMember(4, "kyubee")
    deleteMember(4)
}