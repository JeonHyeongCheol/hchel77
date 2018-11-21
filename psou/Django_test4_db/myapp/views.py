from django.shortcuts import render
from myapp.models import Article

# Django는 ORM을 지원함. 

# Create your views here.
def dbtest(request):
    return render(request, "article.html", {'articles':Article.objects.all()}) 
    # Aritcle Model에 있는 값을 다 가져오는데 object.all()해서 모든 값을 가져옴.