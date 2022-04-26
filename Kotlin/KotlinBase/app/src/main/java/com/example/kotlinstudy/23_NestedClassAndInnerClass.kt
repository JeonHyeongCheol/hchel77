package com.example.kotlinstudy

class `23_NestedClassAndInnerClass` {
    // Title : 중첩클래스와 내부클래스

    // 코틀린에서는 기본적으로 클래스안에 클래스를 하나 더 넣을 수 있는 중첩 클래스(Nested Class)

    // 중첩 클래스(Nested Class)
    // 하나의 클래스가 다른 클래스의 기능과 강하게
    // 연관되어 있다는 의미를 전달하기 위해 만들어진 형식
    // class Outer {
    //   class Nested {
    //   }
    // }
    // Outer.Nested() 로 쓸 수 있음

    // 중첩 클래스 대신 내부 클래스(Inner Class)도 사용 할 수 있음
    // class Outer {
    //   inner class Inner {
    //   }
    // }
    // 혼자서 객체를 만들 수 없고 외부 클래스의 객체가 있어야만 생성과 사용이 가능한 클래스

    // 중첩 클래스 > 외부 클래스의 객체와 다른 객체이기 때문에 내용을 공유 할 수 없음
    // 내부 클래스 > 외부 클래스의 객체 안에서 사용되기 때문에 속성과 함수의 사용이 가능

    fun main() {
        Outer.Nested().introduce()

        val outer = Outer()
        val inner = outer.Inner()

        inner.introduceInner()
        inner.introduceOuter()

        outer.text = "Changed Outer Class"
        inner.introduceOuter()
    }

    class Outer {
        var text = "Outer Class"

        class Nested {
            fun introduce() {
                println("Nested Class")
            }
        }

        inner class Inner {
            var text = "Inner Class"

            fun introduceInner() {
                println(text)
            }

            fun introduceOuter() {
                // 예제와 같이 Outer 클래스와 Inner 클래스에 같은 이름의
                // 속성이나 함수가 있다면 this@OuterClass 이름으로 참조 하면 됨
                println(this@Outer.text)
            }
        }
    }

    // 중첩 클래스와 내부 클래스는 클래스간의 연계성을 표현하여
    // 코드의 가독성 및 작성 편의성이 올라갈 수 있으므로
    // 적절한 상황에서 사용하시는 것이 좋습니다
}