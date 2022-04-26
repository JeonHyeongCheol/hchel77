package com.example.kotlinstudy

class `7_ClassBase` {
    // Title : 클래스의 기본 구조

    // 클래스
    // '값'과 그 값을 사용하는 '기능'들을 묶어 놓은 것
    // 속성 : 고유의 특징 값
    // 함수 : 기능의 구현
    // 클래스 : 속성 + 함수로 구현 된 것

    // 클래스는 '인스턴스'를 만드는 틀이라고 생각해야 함
    // 인스턴스(Instance) : 클래스를 이용해서 만들어 내는 서로 다른 속성의 객체를 지칭하는 용어

    // 자주 사용하는 공통적인 기능은
    // 클래스 내에 함수로 넣어주면 됨
    class Person(var name: String, val  birthYear: Int) {
        fun introduce() {
            println("안녕하세요, ${birthYear}년생 ${name}입니다.")
        }
    }

    fun main() {
        var a = Person("박보영", 1990)
        var b = Person("전정국", 1997)
        var c = Person("장원영", 2004)

        // 클래스 사용법 : 변수명.속성명(. 넣어주어야 함)

        a.introduce()
        b.introduce()
        c.introduce()
    }

    // 코틀린은 객체지향 언어를 기반으로 함수형 언어의 장점을 흡수한 실용적인 언어
    // 따라서 객체지향의 기본 구조가 되는 클래스를 이해하는 것이 중요함
}