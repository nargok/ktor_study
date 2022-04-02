package com.example.testSample

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class NumberTestByStringSpec : StringSpec() {
    init {
        "isOdd:: when value is odd number then return true" {
            val number = Number(1)
            number.isOdd() shouldBe true
        }
    }
}