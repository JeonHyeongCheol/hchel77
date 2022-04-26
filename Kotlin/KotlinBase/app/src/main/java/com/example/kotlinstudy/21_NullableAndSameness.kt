package com.example.kotlinstudy

class `21_NullableAndSameness` {
    // Title : null 값을 처리하는 방법? 동일한지를 확인하는 방법?

    // 우리는 코틀린에서 nullable 변수를 만들어
    // 객체가 들어 있지 않는 null 상태를 만들 수 있음

    // 하지만 null 상태로 속성이나 함수를 쓰려고 하면
    // null pointer exception 이 발생하기 때문에(null 인 객체를 참조하면 발생하는 오류)
    // nullable 변수를 사용 할 때는 null 체크를 꼭 해줘야 함!

    // if문이 아닌 다른 방법은?
    // ?. : null safe operator
    // ?: : elvis operator
    // !!. : non-null assertion operator
    fun main() {
        val a: String? = null
        println(a?.uppercase()) // 참조 연산자를 실행하기 전에 먼저 객체가 null 인지 확인부터하고 객체가 null 이라면 뒤따라 오는 함수를 사용 할 수 없음

        println(a?:"default") // 객체가 null 이 아니라면 그대로 사용하지만 null 이라면 대신 어떤 것을 사용 할 수 있을지 정할 수 있음

        println(a!!.uppercase())
        // 참조연산자를 사용 할 떄 null 여부를 컴파일시 확인하지 않도록 하여
        // 런타임시 null pointer excetpion 이 나도록
        // 의도적으로 방치하는 연산자
    }

    // null safe 연산자는 스코프 함수와 사용하면 더욱 편리함
    fun twoMain() {
        var b: String? = null

        b?.run { // a 가  null 이기 때문에 scope 함수 전체가 실행되지 않음
            println(uppercase())
            println(lowercase())
        }
    }

    // 동일성
    // 동일성에는 두 가지 개념이 있음
    // 내용의 동일성 : 메모리 상에 다른 곳에 할당된 객체라고 해도 그 내용이 같다면 동일하다고 봄
    // 내용의 동일성을 판단하는 연산자는
    // a == b

    // 내용의 동일성은 자동으로 판단되는 것이 아닌
    // 코틀린의 모든 클래스가 내부적으로 상속받는 'Any' 라는
    // 최상위 클래스의 equals() 함수가 반환하는 Boolean 값으로 판단

    // 객체의 동일성 : 서로 다른 변수가 메모리 상에 같은 객체를 가리키고 있으면 동일하다고 봄
    // a === b

    // 기본 자료형에는 자료형의 특징에 따라 equals() 함수가 이미 구현되어 있지만
    // 우리가 커스텀 class 를 만들때는
    // open fun equals(other: Any?):Boolean
    // 동일성을 확인해주는 구문을 별도로 구현해야 함

    fun threeMain() {
        var a = Product("콜라", 1000)
        var b = Product("콜라", 1000)
        var c = a
        var d= Product("사이다", 1000)

        println(a == b) // true
        println(a === b) // false

        println(a == c) // ture
        println(a === c) // true

        println(a == d) // false
        println(a === d) // false
    }

    class Product(val name: String, val price: Int) {
        override fun equals(other: Any?): Boolean {
            if(other is Product)
                return other.name == name && other.price == price
            else
                return false
        }
    }

    // null 처리와 동일성의 확인은 프로그램 작성 도중
    // 빈번하게 사용하는 기능이므로 그 동작 방식들을 잘 알고 사용하여야 함
}