package com.example.kotlinstudy

class `13_HigherOrderFunctionAndLadaFunction` {
    // Title : 고차함수와 라다함수

    // 고차함수(Higher-order function
    // 함수를 마치 클래스에서 만들어 낸 '인스턴스처럼' 취급하는 방법
    // 함수를 '패러미터'로 넘겨 줄 수도 있고
    // '결과값으로 반환' 받을 수도 있는 방법
    // 코틀린에서는 모든 함수를 고차함수로 사용 가능

    fun main() {
        // 고차함수 형태로 넘기려면 함수 이름 앞에 (::) 콜론을 두개 붙여 주면 됨
        b(::a) // (1)
    }

    fun a(str : String) {
        println("$str 함수 a") // (3)
    }
    // 함수의 형식은 대체 어떻게 자료형으로 나탈낼까?
    // 괄호안에 (자료형, 자료형, ...) 패러미터의 자료형을 나열 ->(마이너스와 부등호)를 만든 뒤 자료형의 반환형을 선언
    // 기술한 형태와 같은 형식의 함수는 모두 패러미터로 받을 수 있음
    fun b(function: (String) -> Unit) { // 이 '함수의 형식'은 뒤에 람다 함수에도 사용
        // Unit : 반환형에는 값이 없음
        function("b가 호출한") // (2)
    }

    // 패러미터로 넘길 함수를 굳이 이름까지 붙여 따로 만들 필요 없음
    // 람다함수(lambda function)를 사용!
    // 람다함수는 일반함수와 달리 그 자체가 고차함수이기 떄문에
    // 별도의 연산자 없이도 변수에 담을 수 있음

    fun mainTwo() {
        bTwo(::a)

        val c: (String) -> Unit = { str  -> println("$str 람다함수")} // str은 String으로 받아온 값을 람다함수 내에서 사용할 변수 이름
        // c 와 d 형식으로 사용 할 수 있음
        val d = { str: String -> println("$str 람다함수")}
        // d는 자료형을 기술하지 않음
        // str: String는 람다식 안에만 패러미터의 자료형을 기술
        // (String) -> Unity 자료형으로 저장
    }

    fun aTwo(str : String) {
        println("$str 함수 a")
    }

    fun bTwo(function: (String) -> Unit) {
        function("b가 호출한")
    }

    // 고차함수와 람다함수를 사용하면 이렇게 함수를 일종의 변수로 사용할 수 있다는 편의성도 있지만
    // 이후 배우게 될 컬렉션의 조작이나 스코프 함수의 사용에도 도움이 됨
}