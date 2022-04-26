package com.example.kotlinstudy

class `24_DataClassAndEnumClass` {
    // Title : Data Class 와 Enum Class

    // Data Class
    // 데이터를 다루는 데에 최저과된 class
    // '5가지 기능'을 내부적으로 자동으로 생성 해줌

    // 첫번째
    // 내용의 동일성을 판단하는 equals() 의 자동구현

    // 두번째
    // 객체의 내용에서 고유한 코드를 생성하는 hashcode()의 자동구현

    // 세번째
    // 포함된 속성을 보기쉽게 나타내는 toString()의 자동구현

    // 네번쨰
    // 객체 복사하여 똑같은 내용의 새 객체를 만드는 copy() 의 자동구현
    // copy 사용 시 패러미터가 있다면 해당 패러미터를 교체하여 생성 가능
    // val a = Data("A", 7)
    // val b = a.copy("B") > b는 Data("B", 7)으로 생성

    // 다섯번째
    // 속성을 순서대로 반환하는 componentX() 의 자동구현
    // val a = Data("A", 7)
    // component1는 "A", component2는 7을 반환

    // 사용자가 직접 호출하기 위한 함수가 아닌 배열,
    // 리스트 데이터 클래스에 객체가 담겨 있을 때
    // 자동으로 꺼내 쓸 수 있는 기능을 지원하기 위한 함수들

    fun main() {
        val a = General("보영", 212)

        println(a == General("보영", 212))
        println(a.hashCode())
        println(a)

        val b = Data("루다", 306)
        println(b == Data("루다", 306))
        println(b.hashCode())
        println(b)

        println(b.copy())
        println(b.copy("아린"))
        println(b.copy(id = 618))

        // 새 함수 모두 의미있는 기능으로 자동 구현되어 있음을 알 수 있으며
        // copy() 함수를 통해 원복을 복사한 새 객체 역시 쉽게 만들 수 있음
    }

    class General(val name: String, val id: Int)

    data class Data(val name: String, val id: Int)

    // componentX
    fun twoMain() {
        val list = listOf(Data("보영", 212), Data("루다", 306), Data("아린", 618))
        // 이 리스트에 담긴 Data 객체의 내용을 for 문에서 모두 순회하려면
        // 두 개의 속성을 받을 수 있는 이름을 지정하여 in 앞에 써주시면 됨
        for((a,b) in list) { // a : component1(), b : component2()
            println("${a}, ${b}")
        }
    }

    // Enum Class
    // enumerated Type : '열거형'의 준말
    enum class Color{RED, BLUE, GREEN}
    // 특이한 형태이지만 모두 enum class 인 Color 의 객체를 생성하기 위한 선언

    // enum 클래스 안의 객체들은 관행적으로 상수를 나타낼 때 사용하는 대문자로 기술
    // 또한 enum 의 객체들은 고유한 속성을 가질 수 있음
    // 일반 클래스 처럼 함수도 추가 가능
    enum class ColorTwo(val number: Int) {
        RED(1),
        BLUE(2),
        GREEN(3); // << 세미콜론 후에 함수를 기술해야 함! 주의 할 것!

        fun isRed() = this == ColorTwo.RED
    }

    fun threeMain() {
        var state = State.SING // enum 은 선언시에 만든 객체를 이름으로 참조하여 그대로 사용
        println(state)

        state = State.SLEEP
        println(state.isSleeping())

        state = State.EAT
        println(state.message)
    }

    enum class State(val message: String) {
        SING("노래를 부릅니다"),
        EAT("밥을 먹습니다"),
        SLEEP("잠을 잡니다");

        fun isSleeping() = this == State.SLEEP
    }

    // data class 와 enum class 는
    // 일반 클래스에서 제공되지 않는 특정한 용도의 기능들을
    // 제공하고 있으므로 여러가지 상황에서 유용하게 사용 가능
}