package com.example.kotlinstudy

class `16_AnonymousObjectAndObserverPattern` {
    // Title : 익명객체와 옵저버 패턴

    // Observer
    // '이벤트가 일어나는 것을 감시'하는 감시자의 역할을 만든다고 하여 부르게 됨
    // 키의 입력, 터치의 발생, 데이터의 수신 등
    // 함수로 직접 요청하지 않았지만
    // 시스템 또는 루틴에 의해서 발생하게 되는 동작들을 '이벤트'라고 부르며
    // 이 이벤트가 발생할 때 마다 '즉각적으로 처리' 할 수 있도록 만드는 프로그램밍 패턴을
    // '옵저버 패턴'이라고 부름

    // 옵저버 패턴을 구현 할 때는 두 개의 클래스가 필요
    // 이벤트를 수신하는 Class, 이벤트의 발생 및 전달하는 Class
    // 상호간 작용을 위해 인터페이스를 사용하며 이때 이 인터페이스를 'observer' or 'listener' 라고 함
    // 이렇게 이벤트를 넘겨주는 행위를 'callback' 이라고 함

    // EventPrinter(class) > EventListener(interface) < Counter(class)

    fun main() {
        EventPrinter().start() // (1)
    }

    interface EventListener {
        fun onEvent(count: Int)
    }

    class Counter(var listener: EventListener) {
        fun count() { // (4)
            for(i in 1..100) {
                if(i % 5 == 0) listener.onEvent(i) // (5)
            }
        }
    }

    // 익명 객체를 안쓰는 경우
    /*
    class EventPrinter: EventListener {
        override fun onEvent(count: Int) {
            print("${count} - ") // (6)
        }

        fun start() {
            // this : 키워드가 사용된 '객체 자신'을 참조하는 키워드
            // 여기서 this는 EventPrinter 객체 자신을 나타내지만
            // 받는 쪽에서 'EventListener 만' 요구했기 때문에 EventListener 구현부만 넘겨주게 됨
            // 이를 객체지향의 다형성이라고 함
            val counter = Counter(this) // (2)
            counter.count() // (3)
        }
    }
    */

    // 익명 객체(Anonymous Object) > '이름이 없는 객체'
    // EventListener 를 상속받아 구현하지 않고 임시로 만든 별도의 EventListener 객체를 대신 넘겨줄 수 있음
    class EventPrinter {
        fun start() {
            val counter = Counter(object: EventListener {
                override fun onEvent(count: Int) {
                    print("${count} - ")
                }
            })
            counter.count()
        }
    }

    // observer 패턴은 이벤트를 기반으로 동작하는 모든 코드에서
    // 광범위하게 쓰이는 방식이므로 그 구조를 이해하는 것이 중요
}