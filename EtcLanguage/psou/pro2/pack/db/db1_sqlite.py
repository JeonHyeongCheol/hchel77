'''
Created on 2018. 11. 14.

개인용 DB : sqlite3 - 기몬 모듈로 제공
'''

import sqlite3
print(sqlite3.sqlite_version)
print()
conn = sqlite3.connect('example.db')
#conn = sqlite3.connect(':memory:') # 단발성 DB, Memory에 한번 올라가고 사라짐.

try:
    c = conn.cursor()
    
    #c.execute('drop table if exists jikwon')
    try:
        c.execute("select * from jikwon")
    except Exception as e:
        c.execute('create table jikwon(id integer primarkey, name text)')
    
    # ins data
    c.execute("insert into jikwon values(1, '홍길동')")
    
    # insert시 tuple, list는 가능하지만 set는 불가능.
    tdata = (2,'고길동') # 하나일 때 : execute.
    c.execute("insert into jikwon values(?,?)", tdata)
    
    tdatas = ((3,'신길동'),(4, '나길동')) # 두 개 이상일 때 : executemany.
    c.executemany("insert into jikwon values(?,?)", tdatas)
    
    dicdata = {'id':5, 'name':'공기밥'}
    c.execute("insert into jikwon values(:id,:name)", dicdata)
    
    dicdata2 = {'bun':6, 'ir':'공기밥'}
    c.execute("insert into jikwon values(:bun,:ir)", dicdata2)
    
    # update
    updata = ("김밥", 6)
    c.execute("update jikwon set name=? where id=?", updata)
    
    # delete
    deldata = (6,) # 값이 하나 일때는 튜플 아님!! 꼭 , 써줄 것!!
    c.execute("delete from jikwon where id=?", deldata)
    
    # select
    c.execute("select * from jikwon")
    for r in c: # c는 위에서 설정한 변수 cursor를 의미
        print(str(r[0]) + ' ' + r[1])
    
    print()
    
    c.execute("select * from jikwon")
    for r in c.fetchall():
        print(str(r[0]) + ' ' + r[1])
        
    print()
    
    c.execute("select count(*) from jikwon")
    print("건수 : ", c.fetchone()[0])
    
    
except Exception as e:
    print('err : ', e)