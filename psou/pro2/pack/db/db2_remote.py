'''
Created on 2018. 11. 14.

MariaDB
'''

import MySQLdb

conn = MySQLdb.connect(host = '127.0.0.1', user = 'root', password='123', database='test')
#print(conn)

conn.close()

config = { # dic 타입!
    'host':'127.0.0.1',
    'user':'root',
    'password':'123',
    'database':'test',
    'port':3306,
    'charset':'utf8',
    'use_unicode':True
}

try:
    conn = MySQLdb.connect(**config)
    cursor = conn.cursor()
    
    # 추가
    #sql = "insert into sangdata(code, sang, su, dan) values(10, '부채', 6, 5000)"
    #cursor.execute(sql)
#     sql = "insert into sangdata values(%s, %s, %s, %s)"
#     sql_data = ('11','선풍기', 10, 67000)
#     cursor.execute(sql, sql_data)
#     conn.commit() # remote에서는 commit 꼭 해줘야 함!!
    
    # 수정
    sql = "update sangdata set sang=%s, su=%s, dan=%s where code=%s"
    sql_data = ('안경', 10, 367000, '11')
    cursor.execute(sql, sql_data)
    conn.commit()
    
    # 삭제
    code = '11'
    sql = "delete from sangdata where code=%s"
    cursor.execute(sql, (code,))
    conn.commit()
    
    # 자료 읽기
    sql = "select code, sang, su, dan from sangdata"
    cursor.execute(sql)
    
    for data in cursor.fetchall():
        print("%s %s %s %s"%data)
        
    print()
    
    for data in cursor.fetchall():
        print(data[0], data[1], data[2], data[3])
    
    print()
    
    for (code, sang, su, dan) in cursor:
        print(code, sang, su, dan)
        
    print()
    
    for(a,b,c,d) in cursor:
        print(a,b,c,d)
        
except Exception as err:
    print('err : ', err)
    conn.rollback() # 잘못될 시 롤백

finally:
    cursor.close()
    conn.close()