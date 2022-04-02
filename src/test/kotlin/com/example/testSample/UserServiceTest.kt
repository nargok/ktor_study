package com.example.testSample

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserServiceTest : StringSpec() {
    init {
        "createMessage:: when user name is exist then return message" {
            val userRepository = mockk<UserRepository>()
            val target = UserService(userRepository)

            val id = 100

            every { userRepository.findByName(any()) } returns "Kotest"

            val result = target.createMessage(id)

            result shouldBe "Hello Kotest"
            verify { userRepository.findByName(id) }
        }
        "createMessage:: when user name is not exist then return null" {
            val userRepository = mockk<UserRepository>()
            val target = UserService(userRepository)

            val id = 100

            every { userRepository.findByName(any()) } returns null

            val result = target.createMessage(id)

            result shouldBe null
            verify { userRepository.findByName(id) }
        }
        "createMessage:: when id less than 0 then return null" {
            val userRepository = mockk<UserRepository>()
            val target = UserService(userRepository)

            val id = -1

            every { userRepository.findByName(any()) } returns null

            val result = target.createMessage(id)

            result shouldBe null
            // Blockの処理が呼ばれていないことを検証する exactlyは実行回数
            verify(exactly = 0) { userRepository.findByName(any()) }
        }
    }
}