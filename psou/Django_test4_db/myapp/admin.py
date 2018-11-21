from django.contrib import admin
from myapp.models import Article

# Register your models here.
class ArticleAdmin(admin.ModelAdmin):
    list_display = ('id', 'code', 'name', 'price', 'pub_date') 
    # 그냥 올렸을 때는 하나씩 클릭해야지 볼 수 있음. 하지만 이렇게 하면 한 화면에 전체다 출력해줌.

admin.site.register(Article, ArticleAdmin) # Admin에 올려주면 사이트에 올라감. ArticleAdmin 클래스를 가져와 적용해줌.