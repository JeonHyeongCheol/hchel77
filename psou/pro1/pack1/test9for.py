'''
Created on 2018. 11. 5.

for
'''
#for i in [1,2,3,4,5]: # List
#for i in (1,2,3,4,5): # Tuple

for i in {1,2,3,4,5}: # Set
    print(i, end = ' ')
else:
    print('\nfor 수행')
    
print()

li = [1,2,3]
for i, d in enumerate(li): # 집합형 자료의 순서를 얻을 수 있음.
    print(i, ' ', d)

print()

print('**' * 20)

soft = {'java':"웹용언어", 'python':'만능언어', 'mysql,':'db'}
for i in soft.items():
    #print(i)
    print(i[0] + ' ' + i[1])

print()

for k,v in soft.items():
    print(k + ' ' + v)
    
print()

for i in soft.keys():
    print(i, end = ' ')
    
print()

for i in soft.values():
    print(i, end = ' ')

print("\n")
print('**' * 20)
li1 = [3,4,5]
li2 = [0.5, 1, 2]
for a in li1:
    for b in li2:
        print(a + b, end = ' ')
    print()
    
print()

datas = [a + b for a in li1 for b in li2]
for d in datas :
    print(d, end = ' ')

print()

print('단어 수 출력')
import re

ss = '''남북이 육·해·공 완충구역내 상호 적대행위를 중단하기로 한 가운데, 북한 황해도 개머리 지역 해안포 포문 1개가 닷새째 닫히지 않은 것으로 2일 확인됐다.
최현수 국방부 대변인은 이날 정례브리핑에서 "개머리 진지 (포문 1개 개방) 부분에 대해서는 11월 1일 이후에도 전통문과 수차례 대북 구두통보를 통해서 관련 조치를 북측에 통보하고 있다“고 밝혔다.
남북은 9·19군사합의서에 따라 지난 1일부터 지상·해상·공중 완충구역에서 포사격과 기동훈련, 정찰비행 등 상호 적대행위를 전면 중지키로 했다.
이에 따라 지난 1일 0시부로 서해상에서는 북방한계선(NLL)을 사이에 두고 남북은 포문을 모두 폐쇄했다. 북측도 동·서해에서 100여 문의 포를 폐쇄 조치한 것으로 전해졌다. 다만, 개머리 지역 포문 1개는 첫날 닫지 않았고, 닷새 째인 이날까지 개방해 놓은 상태다.
이 지역은 남측 연평 관측소(OP)에서 12㎞ 정도 떨어진 곳으로, 장재도 뒤쪽에 위치해 있다. 북한군 해안포 4문이 있으며 개방된 포문은 이중 하나이다.
'''


ss2 = re.sub(r'[^가-힣\s]', '', ss) # 한글을 제외한 나머지는 공백으로 치환. \s는 무엇?
print(ss2)
ss3 = ss2.split(' ') # 문자열 자르기.
print(ss3)

cou = {} # 단어의 발생 수를 dict로 저장
for i in ss3:
    if i in cou: # cou 안에 저장
        cou[i] += 1 # 같은 단어는 누적
    else:
        cou[i] = 1 # 없으면 1

print(cou)

print()
for t_str in ['111-1234', '일이삼-사오육칠', '222-1234']:
    if re.match(r'\d{3}-\d{4}$', t_str):
        print(t_str, ' 전화번호 맞네')
    else:
        print(t_str, ' 이건 아니야')
        
print()

from time import localtime
print(localtime())
hour = localtime().tm_hour
print(hour)

act = {6:'잠', 9:'아침먹고 출근', 18:'일하기', 20:'야근', 24:'휴식'} # Dict type
print(act)

for act_time in sorted(act.keys()):
    #print(act)
    if hour < act_time:
        print(act[act_time])
        break
    
print('\n사전형 자료로 과일값 출력')
price = {'사과':2000, '감':500, '배':3000} # Dist type
gogek = {'사과':3, '감':2}
bill = sum(price[f] * gogek[f] for f in gogek)
print('고객이 구매한 과일값은 {}원'.format(bill))

print()

datas = [1, 2, 'a', True, 3]
li = [i * i for i in datas if type(i) == int]
print(li)

print()

datas = {1, 1, 2, 2, 2, 3} # Set type
se = {i * i for i in datas}
print(se)

print()

id_name = {1:'tom', 2:'james'}
name_id = {val:key for key,val in id_name.items()} # 바꿔치기
print(name_id)

print()

aa = [(1,2),(3,4),(5,6)]
print(aa)
for a, b in aa:
    print(a + b)
    
print()
# range를 이용해 수열 값을 얻을 수 있음.
print(list(range(1,6))) # start, end에 맞게 연속으로 출력 해줌.
print(set(range(1,6))) 
print(tuple(range(1,6)))
print(list(range(-10, -50, -5)))
print(list(range(1, 11, 2)))


print()
#for i in range(1, 6):
for i in range(6):
    print(i, end = ' ')

print()

for i in range(2, 10):
    for j in range(1, 10):
        print('{} * {} = {}'.format(i, j, i*j))
    print()
    
# 주사위를 던져서 나온 숫자들의 합이 4의 배수가 되는 경우만 출력
for i in range(6):
    n1 = i + 1
    for  j in range(6):
        n2 = j + 1
        n = n1 + n2
        if n % 4 == 0:
            print(n1, ' ', n2)
            
print()

print('N-gram') # 현재 문자와 다음문자를 묶어서 출력!! 나중에 데이터 분석에서 많이 사용!!
ss = 'hello'

for i in range(len(ss) - 1):
    print(ss[i], ss[i + 1], sep = '')
    
ss2 = 'this is python langu'
words = ss2.split()
print(words)

for i in range(len(words) - 1):
    print(words[i], words[i + 1], sep = '')