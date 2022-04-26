package com.example.kotlinstudy

class `18_Generic` {
    // Title : 제너릭

    // Generic : 클래스나 함수에서 사용하는 자료형을 외붸서 지정할 수 있는 기능

    // Class A(Super Class)
    // Class B(A extends Class)
    // 이 두 클래스의 인스턴스를 공용으로 사용하는 하나의 함수에 패러미터로 받으려면 어떻게 해야 하는가?
    //fun castingExam(var a: A) // 수퍼 클래스인 A 의 자료형으로 받으면 B 를 넣어도 A 로 캐스팅 됨으로서
    // 두 클래스 모두 함수의 패러미터로 사용 할 수 있음

    // 하지만 캐스팅 연산을 거치는 것은 프로그램의 속도를 저하시킬 수 있다는 단점이 있음
    // 그래서 나온 개념이 Generic!

    // 제너릭(Generic)
    // 함수나 클래스를 선언 할 떄 고정적인 자료형 대신
    // 실제 자료형으로 대체되는 타입 패러미터를 받아 사용하는 방법
    // fun <T> genericFunc(param: T): T
    // class GenericClass<T>(var pref : T)
    // T > Int인 경우 전부 T로 바뀌어 컴파일 됨
    // 캐스팅 연산 없이 자료형을 그대로 사용 할 수 있음

    // <T> 타입 패러미터의 이름은 클래스 이름과 규칙이 같지만
    // 일반적으로 'Type'의 이니셜 'T'를 사용하는 것이 관례이며
    // 여러개의 제너릭을 사용할 경우 T 의 다음 알파벳인 <T,U,V>도 사용하긴 함

    // 또한 제너릭을 특정한 수퍼 클래스를 상속받은 클래스 타입으로만 제한하려면
    // <T:SuperClass> T + 콜론 + 슈퍼 클래스명을 명시하면 됨

    // 이렇게 선언된 제너릭은 어떻게 사용하는가?
    // 함수의 경우
    //fun <T> genericFunc(var param:T) {}
    //genericFunc(1) 코드 작성 시 제너릭은 정수 리터럴이라고 생각하고 Int 타입으로 추론 함
    // 클래스의 경우
    //class GenericClass<T>
    //GenericClass<Int> () // 제너릭을 수동으로 지정하거나
    //class GenericClass<T>(var pref: T) 생성자에 제너릭을 지정 할 수 있음
    //함수와 동일하게 해당 타입을 추론 함

    fun main() {
        UsingGeneric(A()).doShouting() // A의 인스턴스를 받았기 때문에 T는 A임
        UsingGeneric(B()).doShouting() // B의 인스턴스를 받았기 때문에 T는 B임
        UsingGeneric(C()).doShouting() // C의 인스턴스를 받았기 때문에 T는 C임
    }

    open class A {
        open fun shout() {
            println("A가 소리칩니다")
        }
    }

    class B : A() {
        override fun shout() {
            println("B가 소리칩니다")
        }
    }

    class C : A() {
        override fun shout() {
            println("C가 소리칩니다")
        }
    }

    class UsingGeneric<T: A> (val t: T) {
        fun doShouting() {
            t.shout()
        }
    }
    // 사실 제너릭을 사용하지 않고
    // UsingGeneric 의 생성자에서 슈퍼클래스인 A를 캐스팅하여
    // 사용하여도 동작은 같겠지만 제너릭을 사용하는 경우
    // 사용할 때 Generic 이 자료형을 대체하게 되어 캐스팅을 방지할 수 있으므
    // 성능을 더 높일 수 있음

    // 제너릭을 함수에 넣을 때
    fun twoMain() {
        doShouting(B())
    }

    fun <T: A> doShouting(t: T) {
        t.shout()
    }

    // 제너릭은 많은 기본 클래스에서도 사용되고 있으니  꼭 알아둬야 함!!
}