package com.example.kotlinstudy

class `10_OverridingAndabstractionAndInterface` {
    // Title : 오버라이딩과 추상화

    // 상속 시 기본적으로 슈퍼 클래스에 있는 함수(같은 이름과 형태를 가진)를
    // 서브 클래스에 선언 할 수 없음
    // 하지만 슈퍼 클래스에서 허용만 한다면 오버라이딩(Overriding)이라는 방법으로 가능함
    // 서브 클래스에서 같은 이름과 형태로 된 함수의 내용을 다시 구현할 수 있음

    fun mainOne() {
        var t = Tiger()

        t.eat()
    }

    open class AnimalOne() {
        open fun eat() {
            println("음식을 먹습니다.")
        }
    }

    class Tiger : AnimalOne() {
        // 상속 받은 경우에 서브 클래스에서는 함수를 재구현 할 수 없음
        // 하지만 슈퍼 클래스에서 함수에 open이 붙은 경우에는 재구현이 가능함

        // 슈퍼 클래스에서 'open'이 붙은 함수는
        // 서브클래스에서 'override'를 붙여 함수를 재구현하면 됨

        override fun eat() {
            println("고기를 먹습니다.")
        }
    }

    fun mainTwo() {
        var r = Rabbit()

        r.eat()
        r.sniff()
    }

    // 오버라이딩과 다르게 슈퍼 클래스에서는 함수의 구체적인 구현은 없고
    // 단지 Animal의 모든 서브클래스는 eat이라는 함수가 '반드시 있어야 한다'는 점만 명시하여
    // 각 서브 클래스가 비어 있는 함수의 내용을 필요에 따라 구현하도록 하려면 추상화라(abstraction)는 개념을 사용해야 함

    // 추상화
    // 선언부만 있고 기능이 구현되지 않은 추상함수(abstraction function)
    // 추상함수를 포함하는 추상 클래스(abstraction class)라는 요소로 구현 됨

    abstract class AnimalTwo {
        abstract fun eat() // 추상함수는 비어 있는 껍데기라고 생각 하면 됨

        // 일반 함수도 선언 가능
        fun sniff() {
            println("킁킁")
        }
        // 이렇게 abstract를 붙인 추상클래스는 일부 함수가 구현되지 않은
        // '미완성 클래스'이기 때문에 단독으로는 인스턴스를 만들 수 없음
        // 따라서 반드시 서브클래스에서 상속을 받아 abstract 표시가 된 함수들을 구현해줘야 함
    }

    class Rabbit : AnimalTwo() {
        override fun eat() {
            println("당근을 먹습니다.")
        }
    }

    // 추상화를 하는 또다른 방법은 인터페이스(Interface)
    // 인터페이스는(Interface)
    // 추상함수로만 이루어져 있는 '순수 추상화 기능'을 말함
    // 코틀린에서는 인터페이스는 속성, 추상함수, 일반함수 모두를 가짐
    // 다만 추상함수는 생성자를 가질 수 있는 반면
    // 인터페이스는 생성자를 가질 수는 없으며
    // 인터페이스에서
    // 구현부가 있는 함수 -> open 함수로 간주
    // 구현부가 없는 함수 -> abstract 함수로 간주
    // 별도의 키워드가 없어도 포함된 모든 함수를
    // 서브클래스에서 구현 및 재정의가 가능 함
    // 또한 한번에 여러 인터페이스를 상속 받을 수 있으므로 좀 더 유연한 설계가 가능함}

    fun mainThree() {
        var d = Dog()

        d.run()
        d.eat()
    }

    interface Runner {
        fun run()
    }
    interface Eater {
        fun eat() {
            println("음식을 먹습니다")
        }
    }

    class Dog : Runner, Eater { // 두 개의 인터페이스의 형식을 모두 물려받아 사용하는 서브 클래스
        override fun run() {
           println("우다다다 뜁니다")
        }

        override fun eat() {
            println("허겁지겁 먹습니다")
        }
    }

    // 정리
    // 이미 구현이 끝난 함수의 기능을 서브 클래스에서 변경해야 할 떄 : 오버라이딩
    // 형식만 선언하고 실제 구현은 서브클래스에 일임할 때 : 추상화
    // 서로 다른 기능들을 여러 개 물려줘야 할 때 : 인터페이스
}