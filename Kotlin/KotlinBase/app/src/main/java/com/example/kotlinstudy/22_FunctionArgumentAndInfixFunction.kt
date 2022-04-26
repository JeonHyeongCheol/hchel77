package com.example.kotlinstudy

class `22_FunctionArgumentAndInfixFunction` {
    // Title : 함수의 Argument를 다루는 방법과 Infix 함수

    // kotlin에서도 overloading을 지원 함(overriding과 혼동X)
    // overloading
    // '같은 Scope 안'에서 같은 함수에 이름을 여려 개 만들 수 있는 기능
    // 파라미터의 자료형이 다르거나, 파라미터의 갯수가 다른 경우 가능
    // 서로 다른 함수로 동작 할 수 있음

    // fun same (x: Int)
    // fun same (x: Int, text: String)
    // fun same (x: Int, y: Int)

    // 다만 파라미터의 이름만 다르고 자료형과 갯수가 같은 경우는 불가능

    fun main() {
        read(7)
        read("감사합니다")
    }

    fun read(x: Int) {
        println("숫자 $x 입니다")
    }

    fun read(x: String) {
        println(x)
    }

    // 패러미터를 받아야 하는 함수이지만
    // 별다른 패러미터가 없더라도 기본값으로 동작해야 한다면 어떻게 해야 함?
    // 이럴 때 default arguments 를 사용!

    fun twoMain() {
        deliveryItem("짬뽕") // count, destination 는 기본 값 사용
        deliveryItem("책", 3) // destination 는 기본 값 사용
        deliveryItem("노트북", 30, "학교") // 그대로 해당 파라미터 값 적
    }

    fun deliveryItem(name: String, count: Int = 1, destination: String = "집") {
        println("${name}, ${count}개를 ${destination}에 배달하였습니다")
    }

    // 그런데, 이름과 목적지만 넣고 개수는 기본값을 이용하고 싶다면 어떻게 함?
    // 이럴 때 named arguments 를 사용!/
    // named arguments
    // 패러미터의 순서와 관계없이 패러미터의 이름을 사용하여
    // 직접 패러미터의 값을 할땅하는 기능

    fun threeMain() {
        deliveryItem("선물", destination = "친구집")
   }

    // 같은 자료형을 개수에 상관없이 패러미터로 받고 싶을 때 사용하는
    // variable number of arguments(vararg)
    // vararg
    // 개수가 지정되지 않은 패러미터라는 특징이 있으므로
    // 다른 파라미터와 사용 할 떄는 맨 끝에 위치해야 함
    fun fourMain() {
        sum(1, 2, 3, 4)
    }

    fun sum(vararg numbers: Int) {
        var sum = 0;
        for(n in numbers) {
            sum += n
        }

        print(sum)
    }

    // 연산자처럼 쓸 수 있는 Infix Function이 있음
    // 함수 정의 시 infix를 붙여서 정의

    fun fiveMain() {
        println(6 multiply 4) // 좌측은 this, 우측은 x
        println(6.multiply(4)) // 위와 동일 동작함
    }

    // 함수 이름을 infix 함수가 적용될 자료형 이름으로 지정
    // 여기서는 Int 값에 사용할 multiply() 함수를 예시로 먄듦
    // 패러미터 역시 Int 값 x 를 지정
    // 반환값 역시 Int 지정
    // this 와 x 를 곱해서 반
    infix fun Int.multiply(x: Int): Int = this * x

    // 참고로 class 안에서 infix 함수를 선언할 떄에는
    // 적용할 클래스가 자기 자신이므로 class 이름은 쓰지 않음

    // 코틀린은 많은 언어들이 지원하는 함수의 다양한 편의기능을
    // 거의 대부분 가지고 있음. 이를 이용하면 더욱 편리한 코딩이 가능해짐
}