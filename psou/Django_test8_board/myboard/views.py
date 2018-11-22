from django.shortcuts import render

from myboard.models import BoardTab


# Create your views here.
def Main(request):
    return render(request, "main.html")

def ListFunc(request):
    datas = BoardTab.objects.all().order_by("-id")
    return render(request, "board.html", {"datas":datas})
    