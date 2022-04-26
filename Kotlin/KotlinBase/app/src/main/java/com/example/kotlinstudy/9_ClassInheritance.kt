package com.example.kotlinstudy

class `9_ClassInheritance` {
    // Title : 클래스의 상속

    fun main() {
        var a = Animal("별이", 5, "개")
        var b = Dog("별이", 5)
        // a, b는 같은 속성을 가진 인스턴스임

        a.introduce()
        b.introduce()

        b.bark()

        var c = Cat("루이", 1)
        c.introduce()
        c.meow()
    }

    open class Animal(var name:String, var age:Int, var type:String) {
        // class Animal 상태는 Open이 아님, 코틀린은 상속 금지가 기본 값
        // "Open"은 클래스 선언시 붙여줄 수 있는 키워드, Open 선언 시 상속 가능
        fun introduce() {
            println("저는 ${type} ${name}이고, ${age}살 입니다.")
        }
    }

    class Dog (name:String, age:Int) : Animal(name, age, "개") {
        // 생성자에서 이름과 나이를 받긴 하지만
        // 클래스의 자체 속성으로 만들어주는 var를 붙이지 말고 일반 파라미터를 받아야 함
        // var, val 등을 붙이면 속성으로 선언됨
        // 상속은 뒤에 :(콜론)을 붙이고 슈퍼 클래스의 생성자를 호출할 수 있도록 해주면 됨

        // 상속받아도 개인 함수를 선언 할 수 있음
        fun bark() {
            println("멍멍")
        }
    }

    class Cat (name:String, age:Int) : Animal(name, age, "고양이") {
        fun meow() {
            println("야옹야옹")
        }
    }

    // 상속
    // 슈퍼 클래스에 존재하는 속성과 '같은 이름'의 속성을 가질 수 없음
    // 서브 클래스가 생성될 때는 반드시 슈퍼 클래스의 생성자까지 호출되어야 함

    // 클래스를 더 구조적으로 다룰 수 있게 해준다는 장점이 있지만
    // 지난친 상속구조는 코드를 더 어렵게 만들 수 있음
}