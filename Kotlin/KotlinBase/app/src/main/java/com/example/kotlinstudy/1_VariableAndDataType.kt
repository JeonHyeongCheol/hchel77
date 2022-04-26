package com.example.kotlinstudy;

// Title : 변수와 자료형

// var
// 일반적으로 통용되는 변수
// 언제든지 읽기 쓰기가 가능함

// val
// 선언시에만 초기화 가능
// 중간에 값을 변경할 수 없음

// 클래스에 선언된 변수
// property(속성)

// 이 외의 Scope 내에 선언된 변수
// Local Variable(로컬변수)

class `1_VariableAndDataType` {

    fun main() {
        var a : Int = 123

        // null을 허용하는 nullable 변수
        // nullable : null인 상태로 연산할 시 null pointer exception 발생 할 수 있으므로 주의 해야
        var b : Int?

        /********************************* 숫자형 *********************************/

        // 정수형
        // Byte 8bits
        // Short 16bits
        // Int 32bits
        // Long 64bits

        // 실수형
        // Float 32bits
        // Double 64bis

        /* 정수 */

        // 32비트 이내의 Int 타입 10진수 기본형, 숫자만으로 표현
        var intValue : Int = 1234
        // 64비트 이내의 Long 타입 10진수, 숫자뒤에 L을 붙여 표현(더 큰 메모리를 사용하는 정수임을 표시)
        var longValue : Long = 1234L
        // 16진수, 앞에 0x를 붙여야 함(x는 Hexadecimal의 약어)
        var intValueByHex : Int = 0x1af
        // 2진수, 앞에 0b를 앞에 붙여야 함(b는 binary의 약어)
        var intValueByBin : Int = 0b10110110
        // kotlin는 8진수의 표기는 지원하지 않음

        /* 실수형 */

        // 실수의 경우 기본이 Double형, 소수점을 포함하여 숫자를 써주거나 필요시 지수 표기법을 추가하여야 함(e는 exponential의 약어)
        var doubleValue : Double = 123.5
        var doubleValueWithExp : Double = 123.5e10

        // Float형, 소문자 또는 대문자 f를 붙여주면 됨(f는 float의 약어)
        var floatValue:Float = 123.5f

        /********************************* 문자형 *********************************/
        // 유니코드 인코딩 중에 한 방식인 UTF-16 BE로 관리
        // 글자 하나하나가 2bytes(16bits)의 메모리 공간을 사용

        // Char의 리터럴
        // 문자는 작은 따움표로 감싸서 사용 함
        var charValue : Char = 'a'
        var koreanCharValue : Char = '가'

        // 특수 문자 지원
        // \t 탭
        // \b 백스페이스
        // \r 첫 열로 커서 옮김
        // \n 개행
        // \' 작은 따옴표
        // \" 큰 따옴표
        // \\ 역 슬래시
        // \$ // $문자
        // \uxxxxx 유니코드 문자

        /********************************* 논리형 *********************************/

        // Boolean 참 또는 거짓
        // true인지 false인지 둘 중에 하나를 저장하는 값
        // Boolean의 리터럴
        var booleanValue : Boolean = true

        /********************************* 문자열 *********************************/

        // val 변수명 = "문자열"
        // 문자열 변수는 코드에서 표기할 때는 따옴표 내에 문자열을 쓰면 됨
        // 여러 줄은 따옴표 3개 사용(줄 바꿈, 특수문자 그대로 문자열로 사용 가능
        val stringValue = "one line string test"
        val multiLineStringValue = """multiline
            string
            test"""

    }
}