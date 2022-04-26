package com.example.kotlinstudy

class `25_SetAndMap` {
    // Title : 컬렉션 2탄, Set 과 Map

    // Set
    // Set 은 List 와 달리 순서가 정렬되지 않으며 중복이 허용되지 않는 컬렉션
    // 인텍스로 위치를 지정하여 객체를 참조할 수는 없으며
    // contains 로 객체가 Set 안에 존재하는 지에 대해서 확인

    // Set<out T>
    // MutableSet<T>

    // List 와 마찬가지로 객체의 추가, 삭제가 가능한지 여부에 따라 사용하게 됨

    // 요소의 추가
    // add(데이터)

    // 삭제
    // remove(데이터)

    fun main() {
        val a = mutableSetOf("귤", "바나나", "키위")

        for(item in a) {
            println("${item}")
        }

        a.add("자몽")
        println(a)

        a.remove("바나나")
        println(a)

        println(a.contains("귤"))

        // 객체의 추가, 삭제, 확인이 잘 됨을 볼 수 있음
    }

    // Map
    // Map 은 객체를 넣을 때 그 객체를 찾아 낼 수 있는 Key 를 쌍으로 넣어주는 컬렉션
    // key : 객체를 찾기위한 값
    // value : key 와 연결된 객체

    // MutableMap.MutableEntry
    // Key, Value

    // 이런 구조 때문에 객체의 위치가 아닌 고유한 키를 통해 객체를 참조하는 특징을 가지고 있음
    // 또한 같은 Key 에 다른 객체를 넣으면 기존의 객체가 대체되니 주의가 필요함

    // Map<K, out V>
    // MutableMap<K, V>

    // 요소의 추가
    // put(키, 값)

    // 삭제
    // remove(키)

    fun twoMain() {
        // key, value 는 to 를 통해 이어 줄 수 있음
        val a = mutableMapOf("레드벨벳" to "음파음파", "트와이스" to "FANCY", "ITZY" to "ICY")

        for(entry in a) {
            println("${entry.key} : ${entry.value}")
        }

        a.put("오마이걸", "번지")
        println(a)

        a.remove("ITZY")
        println(a)

        println(a["레드벨벳"]) // map 의 인덱스 형태로 참조
    }

    // 코틀린이 제공하는 컬렉션 3가지가 있음
    // 이 컬렉션은 그 자체로도 중요하지만 컬렉션 함수와 같이 사용 할 때 매우 유용함
}