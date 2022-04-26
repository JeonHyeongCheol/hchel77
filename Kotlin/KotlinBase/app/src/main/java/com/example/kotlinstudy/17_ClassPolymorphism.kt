package com.example.kotlinstudy

class `17_ClassPolymorphism` {
    // Title : 클래스의 다형성

    // 이미 우리는 앞에서 '클래스의 상속'을 통해
    // 클래스를 '확장'하는 법을 배운 바 있음

    // 클래스를 다형성(Polymorphysm)
    // 예를 들어 음료를 마시는 일에 비유해보자
    // 그냥 목이 말라서 아무 음료나 마셔도 좋을 때도 있지만
    // 기호에 맞춰 콜라, 커피, 생수 등 원하는 음료를 선택 할 때도 있음
    // 이 때 콜라를 '콜라 자체'로도 볼 수 있지만
    // '음료라는 특성'으로도 볼 수 있도록 만드는 것이 '다형성'의 개념

    // Drink(Super Class)
    // Cola(Drink Extends Class)

    // instance of Cola(Drink의 객체 공간 + Cola의 추가 공간)
    //var a: Drink = Cola() // Drink의 객체 공간만 사용 가능, Cola의 추가 공간 사용 불가
    //var b: Cola = Cola() // Drink의 객체 공간, Cola의 추가 공간 둘 다 사용 가능(타입추론 기능으로 Cola 자료형은 생각 가능)
    // 이때 콜라 인스턴스를 음료 변수에 담는 행위를 상위 자료형인 수퍼 클래스로 변환한다고 하여 Up-Casting
    // Up-Casting 된 인스턴스를 다시 하위 자료형으로 변환하면 Down-Casting 이라 부름

    // 사실 Up-Casting 은 그냥 상위 자료형으로 담는 것으로 동작하지만
    // Down-Casting 의 경우 '별도의 연산자'가 필요함
    // as, is를 사용하여 가능

    // as : 변수를 호환되는 자료형으로 변환해주는 캐스팅 연산자
    //var a: Drink = Cola()
    //a as Cola // 선언 후 a 는 Cola 로 동작
    //var b = a as Cola // Cola 로 변환한 결과를 반환받아 변수에 넣을 수도 있음

    // is : 변수가 자료형에 호환되는지를 먼저 '체크한 후 변환' 해주는 캐스팅 연산자
    //var a: Drink = Cola()
    //if(a is Cola)
    //{
    // 이 안에서만 a 가 Cola가 됨
    //}

    fun main() {
        var a = Drink()
        a.drink()

        var b : Drink = Cola() // Drink 타입의 변수이지만 콜라 인스턴스를 담았으므로 Cola 에서 override 한 함수가 실행
        b.drink()

        // 하지만 b는 Drink 변수이므로 이대로는 washDishes 함수를 호출할 수 없음
        // 이때는 is 난 as 를 통해 다운캐스팅을 해야 함

        if(b is Cola) { // b 가 Cola 와 호환되는지 여부를 확인
            b.washDrinks() // is 는 조건문 안에서만 잠시 다운캐스팅 됨
        }

        var c = b as Cola
        c.washDrinks() // 해당 변수에 다운 캐스팅 했기 때문에 바로 washDrinks 호출 가능
        b.washDrinks() // 위에 as를 사용하면서 반환 값 뿐만 아니라 변수 자체도 다운캐스팅 되기 때문임
    }

    open class Drink {
        var name = "음료"

        open fun drink() { // override 가능하도록 선언
            println("${name}를 마십니다")
        }
    }

    class Cola: Drink() {
        var type = "콜라"

        override fun drink() {
            println("${name}중에 ${type}를 마십니다")
        }

        fun washDrinks() {
            println("${type}로 설거지를 합니다")
        }
    }

    // 다형성은 클래스의 상속관계에서 오는 인스턴스의 호환성을
    // 적극 활용할 수 있는 기능으로 수퍼클래스가 같은 인스턴스를
    // 한 번에 관리하거나 인터페이스를 구현하여
    // 사용하는 코드에서도 이용되니 이해가 꼭 필요 함
}