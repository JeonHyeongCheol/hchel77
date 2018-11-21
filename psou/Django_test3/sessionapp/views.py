from django.shortcuts import render
from django.http.response import HttpResponseRedirect

# Create your views here.
def main(request):
    return render(request, "main.html")

def setosFunc(request):
    if "favorite_os" in request.GET: # 들어오는 값이 favorite_os면.
        #print(request.GET["favorite_os"]) # favorite_os라는 키에 대한 값이 들어옴.
        # f_os라는 키로 세션을 생성.
        request.session['f_os'] = request.GET["favorite_os"]
        return HttpResponseRedirect("/showos")
    else:
        return render(request, "setos.html")
    
def showos(request):
    context = {} # context를 dict 타입으로 잡음.
    if "f_os" in request.session: # f_os라는 키가 들어오면.
        context['f_os'] = request.session["f_os"]
        context['message'] = "당신이 선택한 운영체제는 %s"%request.session['f_os']
    else:
        context['f_os'] = None
        context['message'] = "운영체제를 선택하지 못했네요"
        
    request.session.set_expiry(5) # 5초후에 세션 삭제(사라짐).
    
    return render(request, "shows.html", context) # context 값들을 다가지고 넘어감. 