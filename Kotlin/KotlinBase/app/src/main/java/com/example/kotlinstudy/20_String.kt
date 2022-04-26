package com.example.kotlinstudy

class `20_String` {
    // Title : 문자열

    fun main() {
        val test1 = "Test.kotlin.String"

        println(test1.length) // 길이

        println(test1.lowercase()) // 소문자로 변환
        println(test1.uppercase()) // 대문자로 변환

        val test2 = test1.split(".") // 나누는 기준이 되는 문자열
        println(test2)
        // Java와 다르게 Kotlin에서는 정규식이 아닌 일반 문자열을 넣어도 동작함

        println(test2.joinToString())
        println(test2.joinToString("-")) // 문자열 붙임

        println(test1.substring(5..10)) // range 에 해당하는 문자열 반환
    }

    fun twoMain(){
        val nullString: String?  = null
        val emptyString = ""
        val blankString = " "
        val normalString = "A"

        println(nullString.isNullOrEmpty()) // null 및 비었는지
        println(emptyString.isNullOrEmpty())
        println(blankString.isNullOrEmpty())
        println(normalString.isNullOrEmpty())

        // 문자열은 있지만 공백문자로만 이루어진 blank 상태로 비어 있는 것으로 보느냐의 차이
        println(nullString.isNullOrBlank())
        println(emptyString.isNullOrBlank())
        println(blankString.isNullOrBlank())
        println(normalString.isNullOrBlank())

        // 공백
        // Space
        // Tab
        // Line Feed
        // Carrige Return
        // 눈에 직접적으로 보이지 않는 문자들 포함

        fun threeMain() {
            var test3 =  "kotlin.kt"
            var test4 = "java.java"

            println(test3.startsWith("java")) // 시작하는 문자열을 찾기 위함
            println(test4.startsWith("java"))

            println(test3.endsWith(".kt")) // 끝나는 문자열을 찾기 위함
            println(test4.endsWith(".kt"))

            println(test3.contains("lin"))  // 해당하는 문자열이 포함되어 있는지 여부
            println(test4.contains("lin"))

            // 문자열을 다루는 법은 입력값을 받거나
            // 문자열로 된 자료를 처리 할
            // 매우 자주 사용되는 기능
        }
    }
}