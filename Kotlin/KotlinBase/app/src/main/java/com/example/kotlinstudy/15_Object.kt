package com.example.kotlinstudy

class `15_Object` {
    // Title : 오브젝트

    // 기존에 배웠던 class는 단지 인스턴스 객체를 만들기 위한 '틀'임
    // 내부에 있는 속성이나 함수를 사용하려면
    // 생성자를 통해 실체가 되는 인스턴스 객체를 만들어야 했음

    // 여러개의 인스턴스 객체가 필요하지 않고 하나의 객체만으로
    // 공통적인 속성과 함수를 사용해야하는 코드에서는
    // 굳이 class를 쓸 필요없이 Object를 사용하면 됨

    // Singleton Pattern
    // 클래스의 인스턴스를 단 하나만 만들어 사용하도록 하는 코딩 아키텍쳐 패턴

    fun main() {
        println(Counter.count)

        Counter.countUp()
        Counter.countUp()

        println(Counter.count)

        Counter.clear()

        println(Counter.count)
    }

    object Counter {
        // 인스턴스를 생성하지 않는 그 자체에 객체이기 떄문에 생성자 사용하지 않음
        var count = 0

        fun countUp() {
            count++
        }

        fun clear() {
            count = 0
        }
    }

    // object로 선언된 객체는 '최초 사용 시' 자동으로 생성되며
    // 이후에는 코드 전체에서 '공용으로 사용' 될 수 있으므로
    // 프로그램이 종료되기 전까지 공통적으로 사용할 내용들을 묶어 만드는 것이 좋음

    // Companion Object
    // 기존 클래스 안에도 object를 만들 수 있음
    // 클래스의 인스턴스 기능은 그대로 사용하면서
    // 인스턴스간의 공용 속성 및 함수를 별도 만들 수 있음
    // 기능적으로는 기존의 언어들이 가진 Static 멤버와 비슷하다고 생각하면 됨

    // Static
    // 클래스 내부에서 별도의 영역에 고정적으로 존재하여수
    // 인스턴스를 생성하지 않아도 공용으로 사용가능한 속성이나 함

    fun twoMain() {
        var a = FoodPoll("짜장")
        var b = FoodPoll("짬뽕")

        a.vote()
        a.vote()

        b.vote()
        b.vote()
        b.vote()

        println("${a.name} : ${a.count}")
        println("${b.name} : ${b.count}")

        // 서로 다른 인스턴스임에도 companion object 내에 있는 total을 공유하고 있기 때문에
        // 모든 인스턴스에서 투표수를 누적할 수 있기 때문에 어느 인스턴스에도 동일한 total 값을 볼 수 있음
        println("총계 : ${FoodPoll.total}")
    }

    class FoodPoll (val name : String) {
        companion object {
            var total = 0;
        }

        var count = 0;
        fun vote() {
            total++
            count++
        }
    }
}