'''
Created on 2018. 11. 14.

file i/o
'''
import os

try:
    print('파일 읽기')
    print(os.getcwd()) # 현재 경로.
    #f1 = open(r'C:\work\psou\pro1\pack4\testfile.txt', mode = 'r', encoding = "utf-8") # 파일불러오기.
    #f1 = open(os.getcwd() + r'\testfile.txt', mode = 'r', encoding = "utf-8")
    f1 = open('testfile.txt', mode = 'r', encoding = "utf-8") # mode : r - 읽기, w - 쓰기
    print(f1.read())
    f1.close()
    print()
    
    print('파일 저장')
    f2 = open("result1.txt", mode="w", encoding = "utf-8")
    f2.write("일어나\n")
    f2.write("어서\n")
    f2.write("홍길동, 한국인\n")
    f2.close
    print("저장 성공")
    print()
    
    print('파일 추가')
    f3 = open("result1.txt", mode = "a", encoding="utf-8")
    f3.write("\n손오공")
    f3.write("\n저팔계")
    f3.write("\n사오정")
    f3.close
    print('추가 성공')
    print()
    
    print("파일 읽기2")
    f4 = open("result1.txt", mode = "r", encoding="utf-8")
    #print(f4.read())
    #print(f4.readline()) # 한 줄씩 읽기
    #print(f4.readline(1)) # 한글자씩 뺌
    #print(f4.readline(2))
    lines = f4.readlines()
    print(lines)
    f4.close()
    print()
    
    
except Exception as e:
    print('파일 처리 오류 : ', e)