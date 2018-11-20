s1 = "첫번째 자료"
s2 = "2번째 데이터"

print("Content-Type:text/html;charset=utf-8\n")
print(
"""
<html>
<body>
<h1>반가워요</h1>
자료 출력 : {0}, {1}
<br>
<img src='../images/abc.jpg' width='60%' />
<br>
<a href='../main.html'>메인으로</a>
</body>
</html> 
""".format(s1, s2)
)
