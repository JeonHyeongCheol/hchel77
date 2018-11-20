'''
Created on 2018. 11. 20.

클라이언트 값 받기
'''
import cgi

form = cgi.FieldStorage()
name = form["name"].value # request.getParameter("name")과 동일
nai = form["age"].value
gen = form["gen"].value

print("Content-Type:text/html;charset=utf-8\n")
print(
"""
<html>
<body>
이름은 {}, 나이는 {}, 성별은 {}
</body>
</html> 
""".format(name, nai, gen)
)
