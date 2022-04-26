from django.shortcuts import render
from django.views.generic.base import TemplateView

# Create your views here.
def MainFunc(request): # Function
    return render(request, "index.html")

class MyCallView(TemplateView): # Class
    template_name = "callget.html"
    
def InsertFunc(request):
    if request.method == 'GET':
        print('get 요청 처리')
        return render(request, "insert.html")
    elif request.method == 'POST':
        irum = request.POST.get("irum") # get으로 넘어오면 GET, 아니면 Post를 쩌야함. 뒤에 get는 값을 받는 다는 것.
        #irum = request.POST["name"]
        return render(request, "list.html", {"irum":irum})