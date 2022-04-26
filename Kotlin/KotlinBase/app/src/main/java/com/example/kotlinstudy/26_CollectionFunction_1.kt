package com.example.kotlinstudy

class `26_CollectionFunction_1` {
    // title : 컬렉션 함수, 첫번째 이야기

    // 지금까지 Collection 을 for 문으로 반복하여
    // 포함된 아이템을 하나하나 꺼내어 사용함

    // 하지만 코틀린은 함수형 언어의 특징을 가지고 있기 때문에
    // 좀 더 편리하게 컬렉션을 사용 할 수 있었음

    // 컬렉션 함수
    // list 나 set, map 과 같은 컬렉션 또는 배열에
    // 일반 함수 또는 람다 함수 형태를 사용하여
    // for 문 없이도 아이템을 순회하며 참조하거나 조건을 걸고,
    // 구조의 변경까지 가능한 여러가지 함수를 지칭함

    // forEach(for 문과 동일)
    // collection.forEach {
    //      println(it) // it 이라는 변수에 각각의 아이템들이 들어오며, 순회하게 됨
    // }

    // filter
    // collection.filter {
    //      it < 4 // it 에 조건을 걸어주게되면 해당 조건에 맞춰 순회
    // }

    // map
    // collection.map {
    //      it * 2 // it 에 수식을 적용하여 값을 변경하여 collection 에 반환
    // }

    // 이외에
    // any { it == 0 } // 하나라도 조건에 맞으면 true
    // all { it == 0 } // 모두 조건에 맞으면 true
    // none { it == 0 } // 하나도 조건에 맞지 않으면 true

    // first
    // collection.first() // 컬렉션의 첫번째 아이템 반환
    // collection.first { it > 3 } // 람다 형태로 it 조건을 걸으면 조건에 맞는 첫번째 아이템을 반환

    // last
    // collection.last { it > 3 } // 조건에 맞는 아이템을 반환

    // first -> find
    // last -> findLast
    // 로 변경 가능

    // first 와 lsat 함수를 사용할 때 문제가 생길 수 있음
    // 바로 조건에 맞는 객체가 없는 경우(=컬렉션이 비어 있는 경우)
    // NoSuchElementException 발생 할 수 있음
    // firstOrNull, lastOrNull 을 사용 하면 객체가 없는 경우 null 을 반

    // count
    // collection.count() // 컬렉션의 모든 아이템의 개수 반환
    // collection.count { it > 7 } // 조건에 맞는 아이템의 개수 반환

    // 컬렉션 함수들을 사용하면 반복문과 조건문을 사용하는 대부분 대체 할 수 있다는 장점이 있음

    fun main () {
        val nameList = listOf("박수영", "김지수", "김다현", "신유나", "김지우")

        nameList.forEach {
            println(it + " ")
        }
        println()

        println(nameList.filter { it.startsWith("김") })

        println(nameList.map { "이름 : " + it })

        println(nameList.any{ it == "김지연" })
        println(nameList.all{ it.length == 3 })
        println(nameList.none { it.startsWith("이") })

        println(nameList.first{ it.startsWith("김") })
        println(nameList.last{ it.startsWith("김") })
        println(nameList.count { it.contains("지") })

        // 컬렉션 함수는 람다 함수를 사용하여
        // 컬렉션을 좀 더 편리하게 조작할 수 있는 장점을 가진 함수로 경우에 따라
        // 반복문과 조건문 대신 사용하시면 아주 편리함

    }
}