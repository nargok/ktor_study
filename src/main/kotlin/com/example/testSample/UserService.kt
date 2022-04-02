package com.example.testSample

class UserService(private val userRepository: UserRepository) {
    fun createMessage(id: Int): String? {
        if (id < 0) return null
        return userRepository.findByName(id)?.let { "Hello $it" }
    }
}

class UserRepository {
    fun findByName(id: Int): String? {
        return "kotlin"
    }
}