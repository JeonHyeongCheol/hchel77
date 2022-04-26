package com.example.kotlinstudy

class `2_CastAndArrangement` {
    // Title : 형변환과 배열

    // 형변환(Type Casting)
    // 하나의 변수에 지정된 자료형을
    // 호환되는 다른 자료형으로 변경하는 기능

    // 숫자형과 문자형은 변환 가능
    // 논리형은 변환 할 수 없음

    // 기본 자료형들은 자료형 간의 형변환을 지원하기 위해
    // 형변환 함수(Type Casting function)를 제공
    // toByte()
    // toShort()
    // toInt()
    // toLong()
    // toFloat()
    // toDouble
    // toChar

    // Int -> Long 변경
    var a : Int = 54321
    var b : Long = a.toLong()
    // toLong을 하지 않는다면 자료형이 맞지 않는다는 에러남(Type mismatch)
    // 반드시 Int형 변수의 toLong() 함수를 호출하여 Long 값으로 변환된 값을 반환받아 Long변수에 할당 해줘야 함
    // '명시적 형변환(Explicit Type Casting)'이라고 함
    // 명시적 형변환 : 변환될 자료형을 개발자가 직접 지정

    // 코틀린에서는 형변환시 발생할 수 있는 오류를 막기 위해
    // 다른 언어들이 지원하는 '암시적 형변환'을 지원하지 함
    // 암시적 형변환 : 변수를 할당할 시 자료형을 지정하지 않아도 자동으로 형변환

    // 배열(Array)
    // Array<T> << T는 제너릭이라고 함
    // 배열을 만들기 위해 배열로 사용할 변수를 만들어주고
    // arrayOf 함수를 통해 배열에 저장할 값들을 나열하면 됨
    var intArr = arrayOf(1, 2, 3, 4, 5)

    // 혹시라도 특정한 크기의 공간을 가지는 비어있는 배열을 만들고 싶으면 아래와 같이하면 됨
    // 이 때 arrayOfNulls 함수에는 꺾쇠가 들어감
    // 꺾쇠안에는 배열에 할당할 자료형을 지정해주면 됨
    // 꺾쇠안에 값은 Generic이라고 함
    var nullArr = arrayOfNulls<Int>(5)

    // 배열에 값을 할당하거나 사용하려면 아래와 같이 함
    // 다른 언어들 처럼 배열 이름 뒤에 대괄호를 쓰고 그 안에 참조할  Index를 사용
    // Index는 배열내의 데이터 순번이며, 0부터 시작함
    fun arr() {
        intArr[2] = 8
    }

    // 배열은 처음 선언 했을 때 전체 크기를 변경 할 수 없다는 단점이 있지만
    // 한 번 선언을 해두면 다른 자료구조 보다 빠른 입출력 가능하다는 장점이 있음


}