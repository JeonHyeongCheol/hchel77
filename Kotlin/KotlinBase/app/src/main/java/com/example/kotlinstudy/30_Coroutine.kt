package com.example.kotlinstudy

import kotlinx.coroutines.*

class `30_Coroutine` {

    // 모든 구문을 '동기적'으로 실행함
    // 하지만 '여러개의 루틴'을 동시에 실행하여 결과를 내고 싶다면 어떻게 해야하나?
    // '비동기 처리'인 코루틴을 사용해야 함

    // 코루틴(Coroutine)
    // 메인이 되는 루틴과 별도로 진행이 가능한 루틴으로
    // 개발자가 루틴에 실행, 종료를 제어 할 수 있는 단위

    // 코루틴을 사용할 때는
    // import kotlinx.coroutines.* 를 모두 import 하여야 함

    // 코루틴의 Scope
    // 코루틴은 제어범위 및 실행범위를 지정할 수 있음

    // 아래 두 가지를 지원
    // GlobalScope : 프로그램 어디서나 제어, 동작이 가능한 기본 범위
    // CoroutineScope : 특정한 목적의 Dispatcher 를 지정하여 제어 및 동작이 가능한 범위

    // Coroutine 의 dispatcher?
    // CoroutineScope 를 만들 때 적용가능한 Dispatcher
    // Dispatchers.Default : 기본적인 백그라운드 동작
    // Dispatchers.IO : I/O 에 최적화 된 동작
    // Dispatchers.Main // 메인(UI) 스레드에서 동작

    // 이러한 Dispatcher 들은 모든 플랫폼에서 지원되지는 않으니
    // 지원되는 플랫폼에 따라서 사용하여야 함

    // 코루틴은 이러한 Scope 에서 제어되도록 생성 될 수 있음
    // val scope = CoroutineScope(Dispatcher.Default)
    // val coroutineA = scope.launch{}
    // val coroutineB = scope.async{}

    // launch vs async 의 차이는?
    // 반환값이 있는지의 여부

    // launch : 반환값이 없는 Job 객체
    // async : 반환값이 있는 Deffered 객체

    // launch, async 는 모두 람다 함수의 형태를 가지고 있음
    // 그렇기때문에 async 는 마지막 구문에 값이 반환됨

    fun main() {
        val scope = GlobalScope

        // 이대로 하면 실행되지 않음
        scope.launch {
            for(i in 1..5) {
                println(i)
            }
        }
        // 왜냐하면 코루틴은 제어되는 스코프 또는
        // 프로그램 전체가 종료되면 함께 종료되기 때문에
        // 코루틴이 끝까지 실행되는 것을 보장하려면
        // 일정한 범위에서 코루틴이 모두 실행 될 때까지 잠시 기다려주어야 함

        // 위에서 테스트하는 루틴의 경우
        // main() 함수 단 하나이기 때문에
        // 프로세스가 거의 '실행 즉시 종료' 되므로
        // 코루틴도 동작되지 못한 것

        // 이럴 때는
        // runBlocking {
        //  launch {}
        //  async {}
        // }
        // runBlocking 안에서 실행 시
        // 코루틴이 종료 될 때까지
        // 메인 루틴을 잠시 대기시켜 줌

        // 주의할 점
        // 안드로이드에서는 메인 스레드에서 runBlocking 을 걸어주면
        // 일정 시간 이상 응답이 없는 경우
        // ANR(Application Not Responding : 응답 없음 오류)이 발생하며
        // 앱이 강제 종료된다는 점을 유의해야 함

        runBlocking {
            launch {
                for(i in 1..5) {
                    println(i)
                }
            }
        }
    }

    // 루틴의 대기를 위한 추가적인 함수들도 있음
    // delay()
    // delay(milisecond: Long) : milisecond 단위로 루틴을 잠시 대기시키는 함수

    // join()
    // Job.join() : Job 객체에서 호출
    // Job 의 실행이 끝날 때까지 대기하는 함수

    // await()
    // Deferred.await() : Deferred 객체에서 호출
    // Defferred 의 실행이 끝날떄까지 대기하는 함수
    // await() 는 Deferred 의 결과도 반환함

    // 위의 세 함수들은 코루틴 내부 또는 runBlocking{} 과 같은
    // 루틴의 대기가 가능한 구문 안에서만 동작이 가능함

    fun twoMain() {
        runBlocking {
            val a = launch {
                for(i in 1..5) {
                    println(i)
                    delay(10)
                }
            }

            val b = async {
                "async 종료"
            }

            println("async 대기")
            println(b.await())

            println("launch 대기")
            a.join()
            println("launch 종료")
        }
        // async 의 결과를 기다린 후
        // await() 함수에서 결과를 받아 출력하고
        // 다시 launch 가 끝까지 수행되기를 기다린 후
        // launch 가 종료되었음을 출력하는 것을 볼 수 있음
    }

    // 코루틴 실행도중에 중단하는 방법
    // cancel() 함수 사용

    // 코루틴에 cancel()을 걸어주면
    // 다음 두 가지 조건이 발생하며
    // 코루틴을 중단 시킬 수 있음
    // 1. 코루틴 내부의 delay() 함수 또는 yield() 함수가 사용된 위치까지 수행된 뒤 종료됨
    // 2. 코루틴 내부에서 cancel()로 인해 속성인 isActive 가 false 가 되므로 이를 확인하여 수동으로 종료함

    fun threeMain() {
        runBlocking {
            val a = launch {
                for(i in 1..5) {
                    println(i)
                    delay(10)
                }
            }

            val b = async {
                "async 종료"
            }

            println("async 대기")
            println(b.await())

            println("launch 취소")
            a.cancel() // 코루틴 종료
            println("launch 종료")
        }
    }

    // 마지막으로 제한시간 내에 수행되면 결과값을
    // 아닌 경우 null 을 반환하는 withTimeoutOrNull() 함수가 있음
    // 괄호안에 milisecond 단위에 타임아웃 시간을 정해두고
    // 코루틴 구문들을 만든 후 그 결과 값을 받는 형태로 사용
    // 이 함수도 join() 이나 await() 처럼
    // blocking 함수라는 점을 기억해야 함

    fun fourMain() {
        runBlocking {
            var result = withTimeoutOrNull(50) {
                for(i in 1..10) {
                    println(i)
                    delay(10)
                }
                "Finish"
            }

            println(result)
        }
    }
}