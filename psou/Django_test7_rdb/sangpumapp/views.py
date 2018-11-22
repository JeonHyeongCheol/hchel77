from django.shortcuts import render

from sangpumapp.models import Maker, Product


# Create your views here.
def Main(request):
    return render(request, "main.html")

def List1(request):
    makers = Maker.objects.all()
    mcount = len(makers) # 갯수 확인 방법.
    return render(request, "list1.html", {'makers':makers, 'mcount':mcount})
    
def List2(request):
    products = Product.objects.all()
    return render(request, "list2.html", {'products':products})
    
def List3(request):
    mid = request.GET.get("id")
    prod = Product.objects.all().filter(maker_name=mid) # filter : where와 같음.
    return render(request, "list2.html", {"products":prod})