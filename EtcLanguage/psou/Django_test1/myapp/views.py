from django.http.response import HttpResponse
from django.shortcuts import render


# Create your views here.
def index(request): # 기본적으로 내장되어 있는 request.
    return HttpResponse("인덱스 요청 처리")
    # 해당 프로젝트의 모든 요청은 urls로 온다.

def hello(request):
    msg = "장고 만세"
    ss = """
    <html><body>장고<b>프로젝트</b>로 구현 %s</body></html>
    """%(msg,) # 튜플이기 때문에 꼭 콤마!!!!!
    return HttpResponse(ss)

def hello_template(request):
    name = "홍길동"
    return render(request, "hello.html", {"name":name}) 
    # render를 쓰면 templates에 있는 view파일이랑 맵핑 해줌.
    # 값을 줄 때는 dict타입으로 {key:value}을 주면 됨.
    # python은 jsp가 없기 때문에 html로만 사용하여야 함.
    
def hello_img(request):
    return render(request, "world.html") 