'''
Created on 2018. 11. 20.

DB를 이용한 상품 출력 - simplehttp에서는 사용 할 수 없음.
'''
import MySQLdb

config = { # dic 타입!
    'host':'127.0.0.1',
    'user':'root',
    'password':'123',
    'database':'test',
    'port':3306,
    'charset':'utf8',
    'use_unicode':True
}

print("Content-Type:text/html;charset=utf-8\n")
print("""
<html>
<body>
<b> ** 상품자료 </b></br>
<table border='1'>
<tr><th>코드</th><th>품명</th><th>수량</th><th>단가</th></tr>
""")

try:
    conn = MySQLdb.connect(**config)
    cursor = conn.cursor()
    cursor.execute("select * from sangdata")
    datas = cursor.fetchall()
    
    for d in datas:
        print("""
        <tr>
            <td>{}</td>
            <td>{}</td>
            <td>{}</td>
            <td>{}</td>
        <tr>
        """.format(d[0], d[1], d[2], d[3]))

except Exception as e:
    print("err : ", e)
finally:
    cursor.close()
    conn.close()

print("""
</body>
</html> 
""")