package com.example.kotlinstudy

class `28_ConstantsAndLateInitAndLazy` {
    // Title : 변수의 고급 기술. 상수, lateinit, lazy

    // 복습
    // var : 한 번 할당한 객체가 있더라도 다른 객체로 변경하여 할당 할 수 있음
    // val : 한 번 객체를 할당하면 다시 할당된 객체로 바꿀 수 없음

    // 주의 할 점
    // val 은 할당된 객체를 바꿀 수 없을 뿐이지
    // 객체 내부의 속성을 변경 할 수 없는 것은 아니라는 점을 알고 있어야 함

    // 절대 변경이 불가능한 것도 있음
    // '상수' 는 변경 불가함
    // 컴파일 시점에 결정되어 절대 바꿀 수없는 값
    // const val CONST_A = 1234

    // 상수로 선언될 수 있는 값은 기본 자료형만 가능하며(String 자료형 포함)
    // 런타임에 생성될 수 있는 일반적인 다른 클래스의 객체들은 다을 수 없음

    // 클래스의 속성에 선언 불가
    // class Sample {
    //  const val CONST_A = 1234
    // }

    // 지역 변수 등으로 선언 불가
    // fun sample {
    //  const val CONST_A = 1234
    // }

    // 반드시 companion object 에 선언해야 함
    // class Sample {
    //  companion object {
    //      const val CONST_A = 1234
    //  }
    // }

    // 객체의 생성과 관계없이 클래스와 관계된 고정적인 값으로 사용
    // 상수의 이름을 만들 때는 의례적으로 대문자와 언더바(_) 만 사용
    // const val CONST_A = 1234
    // 이를 통해 변수가 아닌 상수라는 점을 알리게 됨
    // 이는 대부분의 언어에서 통용되는 방법

    fun main() {
        val foodCourt = FoodCourt()

        foodCourt.searchPrice(FoodCourt.FOOD_CREAM_PASTA)
        foodCourt.searchPrice(FoodCourt.FOOD_STEAK)
        foodCourt.searchPrice(FoodCourt.FOOD_PIZZA)
    }

    class FoodCourt {
        fun searchPrice(foodName : String) {
            val price = when(foodName) {
                FOOD_CREAM_PASTA -> 13000
                FOOD_STEAK -> 25000
                FOOD_PIZZA -> 15000
                else -> 0
            }
        }

        companion object {
            const val FOOD_CREAM_PASTA = "크림파스타"
            const val FOOD_STEAK = "스테이크"
            const val FOOD_PIZZA = "피자"
        }
    }

    // 위와 보면 굳이 왜 변수를 사용하지 않고 상수를 별도로 사용하는지 궁금하지 않는가?
    // 이는 변수의 경우 런타임 시 객체를 생성하는데 시간이 더 소요되어 성능의 하락이 있기 때문
    // 따라서 늘 고정적으로 사용 할 값은 상수를 통해 겍체의 생성없이
    // 메모리에 값을 고정하여 사용함으로서 성능을 향상 시킬 수 있다는 장점이 있음

    // 늦은 초기화
    // 자료형만 지정해두고 객체는 나중에 할당하면 안되는 것인가?
    // 코틀린에서는 변수를 선언할 때 객체를 바로 할당하지 않는 경우 컴파일이 되지 않음

    // 경우에 따라서 변수에 객체를 할당하는 것을 선언과 동싱 할 수 없을 때도 있음
    // 이럴 때는 lateinit 을 선언
    // lateinit var a: Int
    // 일단 변수만 선언하고 초기값의 할당은 나중에 할 수 있도록 하는 키워드

    // lateinit var 변수의 제한사항
    // 초기값 할당 전까지 변수를 사용할 수 없음(에러 발생)
    // 기본 자료형에는 사용 할 수 없음(String 클래스는 가능)

    // 또한 lateinit 변수의 '초기화'를 하였는지 여부를 확일 할 때는
    // ::a.isInitialized (콜론 2개 + 변수 + .isInitialized)
    // 초기화가 되었는지 확인하여 사용 하여 오류를 막을 수 있음

    fun twoMain() {
        val a = LateInitSample()

        println(a.getLateInitText())
        a.text = "새로 할당한 값"
        println(a.getLateInitText())
    }

    class LateInitSample {
        lateinit var text: String

        fun getLateInitText(): String {
            if(::text.isInitialized)
                return text
            else
                return "기본값"
        }
    }

    // 지연 대리자 속성(lazy delegate properties)
    // 변수를 사용하는 시점까지 초기화를 자동으로 늦춰주는 기능

    // lateinit 과 달리
    // val 변수에 by 라는 키워드를 사용하여 lazy 라는 람다함수 형태의 초기화 함수를 사용하는 형태
    // 코드에서는 선언 시 즉시 객체를 생성 및 할당하여 변수를 초기화하는 형태를 갖고 있지만
    // 실제 실행시에는 val 변수를 사용하는 시점에 초기화 과정을 진행함으로써
    // 코드의 실행시간을 최적화 할 수 있는 코드

    // 참고로 람다함수로 초기화가 진행되므로 함수안에 여러 구문이 들어갈 수 있음
    // 맨 마지막 구문이 변수에 할당 됨

    // val a: Int by lazy {
    //  println("initializing")
    //  7 // 해당 값이 변수에 할당
    // }

    fun threeMain() {

        val number: Int by lazy {
            println("초기화를 시작합니다")
            7
        }

        println("코드를 시작합니다")
        println(number) // 첫번째 실행 시 두 구문이 다 돌고 초기화 됨
        println(number) // 두번째는 초기화 되었기 때문에 7이 할당되는 것을 확인 할 수 있음
    }

    // 상수, 늦은 초기화, 초기화의 지연은 상황에 따라 변수를 사용하는 방법을
    // 조금 더 세세하게 조절 할 수 있다는 장점이 있음
}