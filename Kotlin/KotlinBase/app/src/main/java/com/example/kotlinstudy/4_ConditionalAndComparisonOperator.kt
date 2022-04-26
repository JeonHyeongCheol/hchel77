package com.example.kotlinstudy

class `4_ConditionalAndComparisonOperator` {
    // Title : 조건문과 비교연산자

    // if
    // 조건문의 기본
    // 만약 ~한다면
    // if문에 주어진 값이 '참'이라면 따라오는 구문을 실행하는 기능
    // 참과 거짓을 이용하여 프로그램을 실행할 구문을 구분해주고 있음
    fun own() {
        var a = 7
        if(a > 10) { // 코드가 한줄이면 중괄호 생략 가능
            println("a는 10보다 크다")
        } else {
            println("a는 10보다 작거나 같다")
        }
    }

    // 비교 연산자(Comparison Operators)

    // 부등호
    // <  <= > >= !=

    // 등호
    // ==

    // is 연산자
    // 자료형이 맞는지 체크
    // !is 연산자(느낌표가 Not을 의미)
    // 자료형이 틀린지 체크
    var b = 0;
    fun two() {
        b is Int // 좌측은 변수, 우측은 자료형
        // 좌측 변수가 우측 자료형에 '호환'되는지 여부를 체크
        // 형변환까지 한번에 진행시켜주는 연산자
    }

    // When 연산자
    // 다른 언어에 Switch문을 좀 더 편리하게 바꾼 기능임
    // if가 참과 거짓만을 비교할 수 있는 반면
    // When은 하나의 변수를 여러 개의 값과 비교 할 수 있는 장점이 있음
    fun three() {
        doWhenOne(1)
        doWhenOne("JHC")
        doWhenOne(12L)
        doWhenOne(3.14159)
        doWhenOne("Kotlin")
    }
    fun doWhenOne(a: Any) {
        // Any : 어떤 자료형이든 상관없이 호환되는 최상위 자료형

        when(a) {
            1 -> println("정수 1입니다")
            "JHC" -> println("JHC 코틀린 공부 중")
            is Long -> println("Long 타입 입니다")
            !is String -> println("String 타입이 아닙니다")
            // else : 위의 어떤 조건값에도 맞지 않는 경우 실행
            else -> println("위에 어떤 조건도 만족하지 않습니다")
            // 다만 등호나 부등호의 사용은 불가능 함
            // 참고로 여러개의 조건이 맞을 경우에도 먼저 부합하는 조건이 실행되니 유의해야 함
        }
    }
    // When을 조건에 맞는 동작을 하는 조건문으로서 이용한 경우
    // When의 조건이 맞을 때 동작 대신 값을 반환하는 표현식면(Expressions)으로서의 역할을 하게 하려면
    // When의 조건으로 동작 대신 값을 써주면 됨
    // 이렇게 하면 when의 결과를 변수에 할당하거나 직접 값으로서 사용 할 수 있음
    // When에서 결과를 변수에 받아 출력해보면 의도대로 동작 함
    fun doWhenTwo(a: Any) {
        var result = when(a) {
            1 -> println("정수 1입니다")
            "JHC" -> println("JHC 코틀린 공부 중")
            is Long -> println("Long 타입 입니다")
            !is String -> println("String 타입이 아닙니다")
            else -> println("위에 어떤 조건도 만족하지 않습니다")
        }

        println(result)
    }
}