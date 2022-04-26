package com.example.kotlinstudy

class `5_LoopsAndIncrementOperators` {
    // Title : 반복문과 증감연산자

    // 조건형 반복문
    // 조건이 참인 경우 반복을 유지
    // while, do...while

    // while
    // 해당 조건문이 참일 때까지 반복
    fun one() {
        var a = 0

        while(a < 5) {
            println(a++)
            // 증감 연산자(Increment & Decrement Operators)
            // 증가 연산자(Increment Operators) : 변수의 값을 '1' 증가 시켜주는 역할을 함 > ex) a++, ++a
            // 감소 연산자(Decrement Operators) : 변수의 값을 '1' 감소 시키는 역할을 함 > ex) a--, --a
            // 전위 연산자(Prefix Operators) : 연산자가 포함된 구문에서 이미 증감된 수를 반영하여 진행 됨
            // 후위 연산자(Postfix Operators) : 증가나 감소된 수를 해당 구문에서 사용하지 않고 '다음 구문'부터 사용한다는 차이가 있음

        }
    }

    // do..While
    // while에 의해 조건을 체크하여 반복한다는 점은 같으나
    // 최초 한 번은 조건없이 do에서 구문을 실행한 후 while로 조건을 체크함
    // 선후 관계에 차이가 있음
    // 조건과 상관없이 반드시 한 번은 실행해야 한다면 do...while를 쓰면 됨
    fun two() {
        var a = 0

        do {
            println("조건과 상관없이 한 번은 실행 함")
        } while (a < 5)
    }

    // 범위형 반복문
    // 반복 범위를 정해 반복을 수행

    // for
    // for의 사용법은 고전적인 언어들과는 다름
    // 최신 언어들이 제공하고 있는
    // 조금 더 사람이 이해하기 쉬운 형태로 사용 할 수 있음
    fun three() {
        // in과 ..을 붙여 값의 범위를 정할 수 있음
        // 인덱스로 사용 할 변수에는 var 등을 붙이지 않아도 됨
        // 기본적으로 for문은 값을 1씩 증가시키며 반복하게 됨
        for(i in 0..9) {
            print(i)
        }

        // 뒤에 step을 붙이면 해당 값만큼 증가하여 실행 됨
        for(i in 0..9 step 3) {
            print(i)
        }

        // 감소는 ..대신 downTo라는 키워드를 사용 함
        for(i in 9 downTo 0) {
            print(i)
        }

        // 증가와 동일하게 step을 넣어서 사용 가능
        for(i in 9 downTo 0 step 3) {
            print(i)
        }

        // Char도 가능
        // 증가와 동일하게 step을 넣어서 사용 가능
        for(i in 'a'..'e') {
            print(i)
        }
    }
}