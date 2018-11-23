from django.shortcuts import render

from myboard.models import BoardTab
from _datetime import datetime
from django.http.response import HttpResponseRedirect
from django.core.paginator import Paginator, PageNotAnInteger, EmptyPage


# Create your views here.
def Main(request):
    return render(request, "main.html")

def ListFunc(request):
    datas = BoardTab.objects.all().order_by("-id")
    
    """ 페이지 나누기  """
    paginator = Paginator(datas, 5) # datas에 대해 5개씩 페이지 나눔을 함.
    page = request.GET.get("page")
    try:
        data = paginator.page(page)
    except PageNotAnInteger: # 페이지가 없을 때
        data = paginator.page(1)
    except EmptyPage:
        data = paginator.page(paginator.num_pages())
    
    
    return render(request, "board.html", {"datas":data}) # data는 page지만 값도 같이 넘어감.

def InsertFunc(request):
    return render(request, "insert.html")

def get_ip(request): # IP 받아오기.
    imsi = request.META.get("HTTP_X_FORWARDED_FOR")
    if imsi:
        ip = imsi.split(',')[0]
    else:
        ip = request.META.get("REMOTE_ADDR")
    return ip

def InsertokFunc(request):
    try:
        gbun = 1
        datas = BoardTab.objects.all()
        if datas.count() != 0:
            gbun = BoardTab.objects.latest('id').id + 1
            #print("gbun : ", gbun)
            BoardTab(
            name = request.POST.get("name"),
            passwd = request.POST.get("passwd"),
            mail = request.POST.get("mail"),
            title = request.POST.get("title"),
            cont = request.POST.get("cont"),
            bip = get_ip(request),
            bdate = datetime.now(),
            readcnt = 0,
            gnum = gbun,
            onum = 0,
            nested = 0
            ).save()
    except Exception as e:
        print("Insert Err : ", e)
        
    return HttpResponseRedirect("/board/list") # 추가 후 목록 보기
    
def UpdateFunc(request):
    try:
        data = BoardTab.objects.get(id = request.GET.get("id")) 
    except Exception as e:
        print('Update Read Err : ', e)
        
    return render(request, "update.html", {"data_one":data})

def UpdateokFunc(request):

    try:
        if request.method == "POST":
                upRec = BoardTab.objects.get(id = request.POST.get("id"))
                
                if upRec.passwd == request.POST.get("up_passwd"):
                    upRec.name = request.POST.get("name")
                    upRec.mail = request.POST.get("mail")
                    upRec.title = request.POST.get("title")
                    upRec.cont = request.POST.get("cont")
                    upRec.save()
                
                else:
                    return render(request, "error.html")
                
                return HttpResponseRedirect("/board/list") # 추가 후 목록 보기
                    
    except Exception as e:
        print("Update Err : ", e)
        
    return HttpResponseRedirect("/board/list") # 추가 후 목록 보기

def DeleteFunc(request):
    try :
        data = BoardTab.objects.get(id=request.GET.get("id"))
    except Exception as e:
        print("Delete Err : ", e)
        
    return render(request, "delete.html", {'data':data})

def DeleteokFunc(request):
    if request.method == 'POST' :
        delRec = BoardTab.objects.get(id=request.POST.get("id"))
        
        if delRec.passwd == request.POST.get("del_passwd"):
            delRec.delete()
        else:
            return render(request, "error.html")
        
        return HttpResponseRedirect("/board/list") # 삭제 후 목록보기.

def SearchFunc(request):
    pass

def ContentFunc(request): # 상세보기
    data = BoardTab.objects.get(id=request.GET.get("id")) # 넘어오는 값 : id
    data.readcnt = data.readcnt + 1 # 조회 수
    data.save()
    page = request.GET.get("page") # 넘어오는 값 : page
    
    return render(request, "content.html", {"data_one":data, "page":page})

