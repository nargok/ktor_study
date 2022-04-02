package com.example.testSample

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class NumberTestByBehaviorSpec : BehaviorSpec() {
    init {
        given("isOdd") {
            `when`("num is odd number") {
                val number = Number(1)
                then("return true") {
                    number.isOdd() shouldBe true
                }
            }
            `when`("num is even number") {
                val number = Number(2)
                then("return true") {
                    number.isOdd() shouldBe false
                }
            }
        }
    }
}