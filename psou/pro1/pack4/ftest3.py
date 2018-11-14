'''
Created on 2018. 11. 14.

동이름으로 주소 읽기
'''

try:
    dong = input('동 이름 : ')
    #print(dong)
    
    with open(r'zipcode.txt', mode='r', encoding = "euc-kr") as f:
        line = f.readline()
        #print(line)
        while line:
            #lines = line.split("\t")
            lines = line.split(chr(9))
            #print(lines)
            if lines[3].startswith(dong):
                #print(lines)
                print(lines[0] + " " + lines[1] + " " + lines[2] + " " + lines[3] + " " + lines[4])
            line = f.readline()
except Exception as e:
    print('err : ', e)