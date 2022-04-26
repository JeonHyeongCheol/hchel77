package com.example.kotlinstudy

class `3_TypeInferenceAndFunction` {
    // Title : 타입 추론과 함수

    // 타입 추론(Type Inference)
    // 변수나 함수들을 선언할 때나
    // 연산이 이루어 질 때 자료형을 코드에 명시하지 않아도
    // 코틀린이 자동으로 자료형을 추론해주는 기능

    // 기본 자료형들도 선언시 값을 할당만 해준다면
    // 대부분 자료형을 명시할 필요 없음

    var a = 1234 // Int형 변수로 지정
    var b = 1234L // Long형 변수로 지정
    var c = 12.45 // Double형 변수로 지정
    var d = 12.45f // Float형 변수로 지정
    var e = 0xABCD // 16진수는 Int형으로 추론
    var f = 0b0101010 // 2진수는 Int형으로 추론
    var g = true // Boolean형 자료형 없이 추론 가능
    var h = 'c' // Char형 자료형 없이 추론 가능

    // 따라서 반드시 특정한 자료형으로 지정해야하는 상황이 아니면
    // 대부분은 코틀린의 타입추론 기능을 활용하여 코드량을 줄일 수 있음

    // 함수(Function)
    // 함수는 특정한 동작을 하거나
    // 원하는 결과값을 연산하는데 사용

    // 함수도 function의 준말인 fun으로 시작
    fun main() {
        println(addOne(5, 6, 7))
    }

    fun addOne(a: Int, b: Int, c: Int): Int { // 괄호뒤에 세미콜론 후 반환형을 작성 할 수 있음
        // return
        // 뒤에 오는 값을 반환하는 키워드
        // return이 발생하면 함수의 중간이더라도 값을 반환하고 함수를 종료함
        return a + b + c
    }

    // 단일 표현식 함수(Single-Expression Function)
    // 함수와 같은 기능의 함수를 마치 변수에 결과값을 할당하듯 식을 할당 할 수 있음
    fun addTwo(a: Int, b: Int, c: Int) = a + b + c
    // 단일 표현식 함수는 반환형의 타입 추론이 가능하여 반환형을 생략할 수 있음

}