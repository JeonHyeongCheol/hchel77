'''
Created on 2018. 11. 20.

'''
from django.urls import path

from myapp import views


urlpatterns = [
    path('', views.index), # admin이란 요청이 들어오면 ~ 실행
    # main urls에서 넘어 옴. -> views에 함수 index로 연결.
    path('hello', views.hello),
    path('hello_tem', views.hello_template),
    path('world', views.hello_img)
]
