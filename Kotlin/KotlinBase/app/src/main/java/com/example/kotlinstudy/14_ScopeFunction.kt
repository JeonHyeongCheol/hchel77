package com.example.kotlinstudy

class `14_ScopeFunction` {
    // Title : 스코프 함수

    // 람다함수 복습

    // 첫번째
    // 람다함수도 여러 구문의 사용이 가능
    // 람다함수는 한줄이 아닌 여러줄도 사용 가능
    val calculate: (Int, Int) -> Int = { a, b ->
        println(a)
        println(b)
        a+b // 람다함수에서는 마지막 구문인 a+b의 값을 Int로 반환함
    }

    // 두번째
    // 람다함수에 패러미터가 없다면
    // 실행할 구문들만 나열 하면 됨
    val a: () -> Unit = { println("패러미터가 없어요") }

    // 세번째
    // 패러미터가 하나뿐이라면 it 사용
    // 패러미터가 여러개라면 람다함수 내에서 패러미터 이름을 일일이 써야 함
    val c: (String) -> Unit = { println("$it 람다함수") } // 하나인 경우 패러미터 이름을 생략함

    // 스코프 함수(Scope Function)
    // 함수형 언어의 특징을 좀 더 편리하게 사용할 수 있도록
    // 기본 제공하는 함수들
    // 클래스의 인스턴스를 Scope 함수에 전달하면
    // 인스턴스의 속성이나 함수를 좀 더 깔끔하게 불러 쓸 수 있음

    // 스코프 함수에는
    // apply : 인스턴스를 생성한 후 변수에 담기 전에 '초기화 과정'을 수행할 때 많이 쓰임
    fun main() {
        // 인스턴스를 생성하자마자 그 인스턴스에 참조 연산자를 사용하여 apply를 붙이고
        // 중괄호로 람다 함수를 하나 만들어 apply의 scope 안에서 직접 인스턴스의 속성과 함수를
        // 참조연산자 없이 사용기 가능
        var a = Book("디모의 코틀린", 10000).apply {
            name = "[초특가]" + name
            discount()
        }
        // 또한 apply는 인스턴스 자신을 다시 반환하므로
        // 이렇게 생성되자마자 조작된 인스턴스를 변수에 바로 넣어줄 수 있음

        // apply와 같은 스코프 함수를 사용하면 main함수와 '별도의 scope'에서
        // 인스턴스의 변수와 함수를 조작하므로 코드가 깔끔해진다는 장점이 있음
    }

    class Book(var name : String, var price : Int) {
        fun discount() {
            price -=2000
        }
    }

    // run
    // apply 처럼 run 스코프 안에서 참조연산자를 사용하지 않아도 된다는 점은 같지만
    // 일반 람다함수처럼 인스턴스 대신 마지막 결과 값을 반환
    // 따라서 이미 인스턴스가 만들어진 후에 인스턴스의 함수나 속성을 Scope 내에서 사용해야 할 떄 유용
    /*
    var b = a.run {
        println(a.price)
        a.name
        // 이렇게 쓰면 가격은 출력하지만 마지막 구문인 이름은 반환하여 b라는 변수에 할당
    }
    */

    fun twoMain() {
        var a = Book("디모의 코틀린", 10000).apply {
            name = "[초특가]" + name
            discount()
        }

        // 변수 a에 참조 연산자를 사용하여 run을 붙이고
        // 중괄호 안에서 인스턴스의 속성 이름을 직접 사용하면
        a.run {
            println("상품명 : ${name}, 가격 : ${price}원")
       }
    }

    // with
    // run 과 '동일한 기능'을 가지지만 단지 인스턴스를 참조연산자 대신
    // 패러미터롤 받는다는 차이 뿐
    // a.run{...}
    // with(a) {...}

    // also
    // let
    // apply / also : 처리가 끝나면 인스턴스를 반환
    // run / let : 처리가 끝나면 최종값을 반환

    // 다만 한가지 공통적인 '차이점;이 있음
    // also, let : 마치 패러미터로 인스턴스를 넘긴것처럼

    // 이 두 함수는 왜 굳이 패러미터를 통해서 인스턴스를 사용하는 귀찮은 과정을 거칠까?
    // 이는 같은 이름의 변수나 함수가 'scope 바깥에 중복'되어 있는 경우에
    // 혼란을 방지하기 위해서 함

    fun threeMain() {
        var price = 5000
        // 이는 run함수가 인스턴스 내의 price 속성보다
        // run 이 속해있는 'main 함수'의 price 변수를 우선시 하기 때문임

        var a = Book("디모의 코틀린", 10000).apply {
            name = "[초특가]" + name
            discount()
        }

        a.run {
            println("상품명 : ${name}, 가격 : ${price}원")
        }

        // run을 대체
        a.let {
            println("상품명 : ${it.name}, 가격 : ${it.price}원")
       }

        // apply 역시 같은 경우가 있다면
        // 'also'로 대체하여 사용하면 됨

        // 스코프 함수는 인스턴스의 속성이나 함수를
        // Scope 내에서 깔끔하게 분리하여
        // 사용할 수 있다는 점 때문에 코드의 가독성을 향상시킨다는 장점이 있음
    }
}