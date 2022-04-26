package com.example.kotlinstudy

class `19_List` {
    // Title : 컬렉션 1탄, 리스트

    // '데이터를 모아 관리'하는 Collection.class를
    // 상속 받는 Sub Class  가장 단순한 형태로
    // 여러개의 데이터를 원하는 순서로 넣어 관리하는 형태

    // 리스트에는 두 가지가 있음
    // 첫번째 List<out T>
    // 두번째 MutableList<T>
    // 두 클래스의 차이는 이름에 붙은 Mutable(변할 수 있는)이 말해주듯
    // List<out T> : 생성시에 넣은 객체를 대체, 추가, 삭제 할 수 없음
    // MutableList<T> : 생성시에 넣은 객체를 대체, 추가, 삭제가 가능함

    // 리스트를 만들 때는 전용함수인
    // listOf(1,2,3)
    // mutableListOf("A", "B", "C")
    // 또한 MutableList 에서는
    // 요소의 추가
    // add(데이터), add(인덱스, 데이터)

    // 삭제
    // remove(데이터)
    // removeAt(인덱스)

    // 무작위 섞기
    // shuffle()

    // 정렬
    // sort()
    // 등의 함수를 사용 할 수 있음

    fun main() {
        var a = listOf("사과", "딸기", "배")
        println(a[1])

        for(fruit in a) {
            println("${fruit}")
        }

        println()

        val b = mutableListOf(6, 3, 1)
        println(b)

        b.add(4)
        println(b)

        b.add(2, 8)
        println(b)

        b.removeAt(1)
        println(b)

        b.shuffle()
        println(b)

        b.sort()
        println(b)

        // 리스트는 목록이 필요한 모든 코드에서 가장 편리하게 사용 할 수 있는 컬렉션
    }
}