package com.example.kotlinstudy

class `6_FlowControlAndLogicalOperators` {
    // Title : 흐름제어와 논리연산자

    /* 흐름 제어 */
    // return
    // 함수를 종료하고 값을 '반환'하는 역할을 하고 있음

    // break
    // 반복문 내의 구문이 실행되는 중간에
    // 즉시 반복문을 '종료'하고
    // 다음 구문으로 넘어가는 역할을 함

    // continue
    // 다음 반복 조건으로 즉시 넘어가는 역할을 함

    fun one() {
        for(i in 1..10) {
            if(i == 3) break // 1과 2만 찍힘
            println(i)
        }

        for(i in 1..10) {
            if(i == 3) continue // 3을 제외한 나머지가 찍
            println(i)
        }
    }

    // 코틀린에서의 기능 추가
    // 다중 반복문에서 break나 continue가 적용되는 반복문을
    // label을 통해 지정할 수 있는 기능
    fun two() {
        for (i in 1..10) {
            for (j in 1..10) {
                if(i == 1 && j == 2) break
            }
            // 고전적인 언어에서는 안에서 break를 걸어도 외부 반복문에서 또 다시 조건 체크를 해야 했음
        }

        // 코틀린에서는 외부 반복문에 레이블 이름과 @기호를 달고
        // break 문에서 @과 레이블 이름을 달아주면 레이블이 달린 반복문을 기준으로 즉시 break 시켜줌(continue도 동일 함)
        loop@for (i in 1..10) {
            for (j in 1..10) {
                if(i == 1 && j == 2) break@loop
                println("i : $i, j : $j")
            }
        }
    }

    /* 논리 연산자 */
    // 논리 연산자(Logical Operators)
    // 논리 값을 연산하여
    // 새로운 논리값을 도출할 때 쓰는 연산

    // &&(And 연산자)
    // ||(OR 연산자)
    // !(Not 연산자)
}