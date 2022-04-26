package com.example.kotlinstudy

class `8_ClassConstructor` {
    // Title : 클래스의 생성자

    // 생성자(Constructor)
    // 새로운 인스턴스를 만들기 위해 호출하는 특수 함수
    // 생성자를 호출하면 클래스의 인스턴스를 만들어 반환 받을 수 있음
    // 인스턴스의 속성을 초기화, 인스턴스 생성시 구문을 수행

    // init
    // 함수를 통해 패러미터나 반환형이 없는 특수한 함수
    // 생성자를 통해 인스턴스가 만들어 질때 호출되는 함 수
    class PersonOne(var name:String, val birthYear:Int) {
        init{
            // this
            // 인스턴스 자신의 속성이나 함수를 호출하기 위해
            // 클래스 내부에서 사용되는 키워드
            println("${this.birthYear}년생 ${this.name}님이 생성되었습니다.")
        }
    }

    fun main() {
        var a = PersonOne("박보영", 1990)
        var b = PersonOne("전정국", 1997)
        var c = PersonOne("장원영", 2004)

        var d = PersonTwo("이루다")
        var e = PersonTwo("차은우")
        var f = PersonTwo("류수정")
    }

    // 기본 생성자 : 클래스를 만들 때 기본으로 선언
    // 보조 생성자 : 필요에 따라 추가적으로 선언

    // 보조 생성자(Secondary Constructor)
    // 기본 생성자와 다른 형태의 생성자를 제공하여
    // 인스턴스 생성시 편의를 제공하거나
    // 추가적인 구문을 수행하는 기능을 제공하는 역할을 함

    // 보조 생성자를 만들 때는 반드시 기본 생성자를 통해 속성을 초기화 해줘야 함
    // 보조 생성자가 기본 생성자를 호출하도록 하려면 콜론(:)을 붙인 후 this라는 키워드를 사용
    // 기본 생성자가 필요로 하는 패러미터를 괄호안에 넣어주면 됨
    class PersonTwo(var name:String, val birthYear:Int) {
        init{
            println("${this.birthYear}년생 ${this.name}님이 생성되었습니다.")
        }
        constructor(name:String) : this(name, 1997) {
            println("보조 생성자가 사용되었습니다.")
        }
    }

    // 기본 생성자와 보조 생성자는 클래스를 사용하는 사람에게 다양한 방법으로
    // 인스턴스를 생성하는 법을 제시함으로써 편의를 제공하는 기능
}

