from django.shortcuts import render

from myguest.models import Guest
from _datetime import datetime
from boto.connection import HTTPResponse
from django.http.response import HttpResponseRedirect


# Create your views here.
def MainFunc(request):
    return render(request, "main.html")

def ListFunc(request):
    # 정렬 할 수 있는 방법 1, 2번은 Model에 있음.
    gdata = Guest.objects.all().order_by("title") # 정렬은 order_by메소드를 쓰면 됨. default : asc(오름 차순)
    #gdata = Guest.objects.all().order_by("-title") # -를 붙이면 desc(내림차순)
    #gdata = Guest.objects.all().order_by("-title")[0:2] # 슬라이싱
    return render(request, "list.html", {'gdata':gdata})

def InsertFunc(request):
    return render(request, "insert.html")

def InsertFuncOk(request):
    if request.method == "POST":
        Guest( # Guest 클래스로 다가.
            myno = request.POST.get("myno"), # 컬럼명 = 받는 값(넘어오는 파라미터 값).
            title = request.POST.get("title"),
            content = request.POST.get("content"),
            regdate = datetime.now(),
        ).save() # save를 쓰면 Insert가 됨.
        
    return HttpResponseRedirect("/guest") # 추가 후 목록 보기!!.