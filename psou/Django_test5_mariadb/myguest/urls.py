from django.urls import path
from myguest import views

urlpatterns = [
    path('', views.ListFunc), # 목록보기.
    path('insert', views.InsertFunc),
    path('input', views.InsertFuncOk),
]