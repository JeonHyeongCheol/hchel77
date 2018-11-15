'''
Created on 2018. 11. 14.


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


try:
    conn = MySQLdb.connect(**config)
    cursor = conn.cursor()
    
    
    buser_bun = input("부서번호를 입력하세요 : ")
    sql = """
    select jikwon_no, jikwon_name, buser_name, jikwon_jik 
    from buser left join jikwon 
    on buser_num = buser_no 
    where buser_no = %s
    """
    cursor.execute(sql, (buser_bun,))

    cou = 0
    for data in cursor.fetchall():
        cou += 1
        print(data[0], data[1], data[2], data[3])
    
    print("인원수 : ", cou, "명")


except Exception as err:
    print('err : ', err)
    conn.rollback() # 잘못될 시 롤백

finally:
    cursor.close()
    conn.close()