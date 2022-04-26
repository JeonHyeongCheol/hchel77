'''
Created on 2018. 11. 14.

with 문 사용 - 내부적으로 close()를 함
'''

try:
    # 저장 # with을 쓰면 따로 close를 안해줘도 됨.
    with open("result2.txt", mode="w", encoding="utf-8") as f1:
        f1.write("파이썬으로 문서저장\n")
        f1.write("with 문서저장\n")
        f1.write("명시적인 close() 없다")
    print("저장 완료")
    print()
    
    with open("result2.txt", mode="r", encoding="utf-8") as f2:
        print(f2.read())
except Exception as e :
    print('오류  : ', e)
    
print("\n 피클링(복합 객체 처리 - object type으로 파일 저장)")
import pickle

# dic, list, tuple은 휘발성, 사용할 때만 불러옴.

dicdata = {'tom':'111-1111', '길동':'222-2222'}
listdata = ['마우스', '키보드']
tupledata = (dicdata, listdata)
    
with open('hello.dat', 'wb') as f3:
    pickle.dump(tupledata, f3) # dump : 저장.
    pickle.dump(listdata, f3)
    
print('객체를 파일로 저장 완료')

print('읽기')
with open('hello.dat', 'rb') as f4:
    a, b = pickle.load(f4) # load : 읽기.
    print(a)
    print(b)
    c = pickle.load(f4)
    print(c)