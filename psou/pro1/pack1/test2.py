'''
Created on 2018. 11. 1.

연산자
'''
from datashape.typesets import boolean

v1 = 3
v1 = v2 = v3 = 5
print(v1, v2, v3)
print('파이썬', end = ',') # 파이썬은 기본적으로 Line Skip함, end = : 옵션을 사용해 구분자를 줄 수 있음, 공백 넣으면 라인 스킵하지않고 커서가 옆에 붙음
print('만세')
v1 = 1, 2, 3
print(v1)
v2, v3 = 10, 20 # v2 = 10, v3 = 30
v3, v2 = v2, v3 # 값 바꾸기
print(v2, v3) # 20, 10 나옴

print('값 할당(packing)')
v1, *v2 = [1, 2, 3, 4, 5] # 들어가야 되는 값의 갯수와  변수의 갯수가 맞아야지 들어감, 안그러면 ERR!!, *(와일드카드)를 쓰면 앞에만 v1, 나먼지는 v2가 가짐
*v1, v2 = [1, 2, 3, 4, 5] # v2만 5를가지고 나머지는 v1이 가짐 
print(v1)
print(v2)

v1, v2, *v3 = [1, 2, 3, 4, 5]
v1, *v2, v3 = [1, 2, 3, 4, 5] # 앞 뒤만 빼고 나머지가 중간에 들어감
print(v1, v2, v3)
print()

print(format(1.5678, '10.3f')) # 10는  앞의자리 10자리. 3f는 소수자리 3자리
print('나는 나이가 %d 이다.'%23) # %가 문자열이랑 맵핑 함
print('나는 나이가 %s 이다.'%'스물셋')
print('나는 나이가 %d 이고 이름은 %s이다.'%(23, '홍길동'))
print('나는 나이가 %s 이고 이름은 %s이다.'%(23, '홍길동'))
print('나는 키가 %f이고, 에너지가 %d%%.'%(177.7, 100))
print('이름은 {0}, 나이는 {1}'.format('한국인', 33))
print('이름은 {}, 나이는 {}'.format('신선해', 33))
print('이름은 {1}, 나이는 {0}'.format(34, '강나루'))
print()

# 연산자
print("\n\n 연산자 연습------------------------------------------------")
print(5 + 3, 5 - 3, 5 * 3, 5 / 3)
print(5 // 3, 5 % 3, divmod(5, 3)) # divmod : 몫과 나머지를 분리해줌

print(3 + 4 * 5, (3 + 4) * 5)
print()

print(5 > 3, 5 == 3, 5 != 3)
print(5 > 3 and 4 < 3, 5 >= 3 or 4 <= 3, not(3 > 2))
print()

print("한" + "국" + "만세") # 문자열 더하기
print('한국'* 20) # 문자열 곱하기
print()

print()
a = 10
a = a + 1
a += 1 # 파이썬에는 증감 연산자 없음
print(a)

print('부호 변경: ', a, a * -1, -a, --a) # --a : 부호를 바꿨다가 다시 바꾼것과 동일
print()

print('boolean : ', bool(0), bool(0.0), 
      bool(1), bool(10), bool(-1.1)) # 파이썬에서는 0만(소수점 포함), False, 나머지는 True
print('boolean : ', bool(False), bool(None), bool(""), # 전부 False
      bool([]), bool({}))

print('참고')
print(r'c:\aa\nbc\table') # 순수 데이터를 줄때 앞에 r을 써줘야 함
