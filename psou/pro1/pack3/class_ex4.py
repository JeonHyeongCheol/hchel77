'''
Created on 2018. 11. 7.

'''
class My:
    a = 1
    
print("My.a : ", My.a) # 바로 클래스 변수 값 가져옴.
print()

my1 = My()
print("my1.a : ", my1.a) # my1에 들렸다가 없으면 원형클래스로 감.
print()

my1.a = 2
print("my1.a : ", my1.a)
print()

my2 = My()
print("my2.a : ", my2.a)
my2.a = 3
print("my2.a : ", my2.a)
my2.b = 4
print("my2.a : ", my2.b)

# print(my1.b) # error
# print(My.b) # error