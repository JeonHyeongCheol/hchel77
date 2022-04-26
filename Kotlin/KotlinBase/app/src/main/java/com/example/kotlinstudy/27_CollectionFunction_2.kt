package com.example.kotlinstudy

class `27_CollectionFunction_2` {
    // Title : 컬렉션 함수, 두번째 이야기

    // associateBy
    // 아이템에서 key 를 추출하며 map 으로 변환하는 함수

    // List 에 있는 객체들을 Key, Value 로 Map 으로 변경 할 시 사용
    // collection.associateBy { it.name }

    // groupBy
    // key 가 같은 아이템 끼리 배열로 묶어 map 으로 만드는 함수

    // 특정한 값을 Key 로 지정하여 해당 값을 가진 객체끼리
    // 묶은 배을을 Value 로 하는 Map 으로 변경 할 시 사용
    // collection.groupBy { it.birthYear }

    // partition
    // 아이템에 조건을 걸어 두 개의 컬렉션으로 나누어 줌

    // 아이템에 조건을 걸어 true, false 인지에 따라 두 개의 컬렉션으로 나누어 줌
    // 두 개의 컬렉션은 두 객체를 담을 수 있는 Pair 라는 클래스 객체로 반환 됨
    // first, second 로 참조하여 사용 하면 됨
    // collection.partition { it.birthYear > 2002 }

    // Pair 를 직접 받아 줄 수 있도록 변수에 타입을 지정 할 수 있음
    // val (over2002, under2002) = collection.partition { it.birthYear > 2002 }

    fun main () {
        data class Person(val name: String, val birthYear: Int)

        val personList = listOf(Person("유나", 1992), Person("조이", 1996), Person("츄", 1999), Person("유나", 2003))

        println(personList.associateBy { it.birthYear })
        println(personList.groupBy { it.name })

        val (over98, under98) = personList.partition { it.birthYear > 1998 }
        println(over98)
        println(under98)
    }

    // flatMap
    // 아이템마다 만들어진 컬렉션을 합쳐서 반환하는 함수
    // 중괋호 안에서 아이템마다 컬렉션을 생성하면 이를 합쳐서 하나의 컬렉션으로 반환
    // collection.flatMap {}

    // 기존 컬렉션 2, 3
    // collection.flatMap {
    //      listOf(it * 3, it + 3)
    // }
    // 결과 컬렉션 6, 5, 9, 6 <<<<< 2 * 3, 2 + 3, 3 * 3, 3 + 3

    // getOrElse() {}
    // 인덱스 위치에 아이템이 있으면 아이템을 반환하고 아닌 경우 지정한 기본값을 반환하는 함수
    // collection.getOrElse() {}

    // 기존 컬렉션 6, 5, 9, 6
    // collection.getOrElse(1) {50} > 결과 인덱스 1에 5를 반환
    // collection.getOrElse(8) {50} > 결과 인덱스가 없으므로 대괄호에 있는 50 반환

    // zip
    // collectionA zip collectionB
    // 컬렉션 두 개의 아이템을 1:1로 매칭하여 새 컬렉션을 만들어 줌
    // Pair 클래스에 객체로 만들어 List 로 반환
    // 이 때 결과 List 의 아이템의 개수는 더 작은 컬렉션을 따라가게 됨

    // collectionA : a, b, c, d
    // collectionB : 1, 2, 3, 4
    // A zip B : List Pair(A, 1), Pair(B, 2), Pair(C, 3), Pair(D, 4)

    fun twoMain() {
        val numbers = listOf(-3, 7, 2, -10, 1)

        println(numbers.flatMap { listOf(it * 10, it + 10) })

        println(numbers.getOrElse(1) { 50 })
        println(numbers.getOrElse(10) { 50 })

        val names = listOf("A", "B", "C", "D")

        println(names zip numbers)
    }

    // 컬렉션 함수는 데이터의 조작을 더 수월하게 만들 수 있는
    // 다양한 기능을 갖고 있으므로 컬렉션에 담긴 데이터를 다룰 때는 꼭 사용 하는 것이 좋음
}