package com.example.kotlinstudy

class `12_AccessScopeAndAccessLimiter` {
    // Title : 변수, 함수, 클래스의 접근 범위와 접근 제한자

    // Scope : (주제조직활동 등이 다루는) 범위

    // 스코프(Scope) : 구조내에서 변수나 함수, 클래스의 '공용 범위'를 제어하는 단위
    // 접근제한자 : 스코프 외부에서 스코프 내부로의 접근을 제어

    // 변수나 함수, 클래스 같은 '멤버'들을 서로 공유하여 사용 할 수 있는 범위를 지정해 둔 단위 입니다.
    // 스코프가 지정되는 범위는 패키지, 클래스, 함수

    // 스코프의 규칙
    // 첫번째
    // 스코프 외부에서는 스코프 내부 멤버를 '참조연산자'로만 참조가 가능함

    // 두번째
    // 동일 스코프내에서는 멤버들을 '공유'할 수 있습니다.

    /*
    val a = "패키지 스코프"

    class B {
        fun print() {
            println(a)
        }
    }

    fun main() {
        println(a)
        B().print()
    }
    */

    // 세번쨰
    // 하위 스코프에서는 상위 스코프의 멤버를 재정의 할 수 있음
    // 원래 스코프의 같은 레벨에서는 같은 이름의 멤버를 만들어서는 안 됨
    // var a = "너두나두"
    // var a = "야나두"
    // 위와 같이 선언 할 경우 conflicting declarations(선언부가 서로 충돌한다는 뜻) error가 발생
    // 하지만 하위 스코프에서는 같은 이름의 멤버를 만들어 사용 할 수 있음

   val a = "패키지 스코프"

   class B {
       val a = "클래스 스코프"
       fun print() {
           println(a)
       }
   }

   fun main() {
       val a = "함수 스코프"
       println(a)
       B().print()
   }

    // 접근 제한자(Access Modifier)
    // 이렇게 스코프 외부에서 스코프 내부에 접근 할 때
    // 그 권한을 '개발자가 제어' 할 수 있는 기능
    // 접근 제한자에는
    // public
    // internal
    // private
    // protected
    // 이 접근 제한자들은
    // 변수 private var a = "..."
    // 함수 public fun b {...}
    // 클래스 internal class C{...}
    // 선언 시 맨 앞에 붙여 사용 함

    // 패키지 스코프에서는
    // public(기본값) : 어떤 패키지에서도 접근 가능
    // internal : 같은 모듈 내에서만 접근 가능
    // private : 같은 파일 내에서만 접근 가능
    // protected 는 패키지 스코프에서는 사용하지 않음

    // 클래스 스코프에서는
    // public(기본값) : 클래스 외부에서 늘 접근 가능
    // private : 클래스 내부에서만 접근 가능
    // protected : 클래스 자신과 상속받은 클래스에서 접근 가능
    // internal 은 클래스 스코프에서 사용하지 않음

    // 스코프는 멤버들의 가용 범우를 지정해 둔 단위로
    // 개발자는 의도에 따라 스코프 안에 변수나 함수, 클래스를 배치할 수 있으며,
    // 접근 제한자는 이러한 스코프의 외부와 내부에서 사용할 멤버를 분리하여
    // 스코프 외부에서 건드리지 말아야 할 기능이나 값들을
    // 안전하게 제한하는 용도를 가지고 있습니다.

}