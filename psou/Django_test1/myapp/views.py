from django.http.response import HttpResponse
from django.shortcuts import render


# Create your views here.
def index(request): # 기본적으로 내장되어 있는 request.
    return HttpResponse("인덱스 요청 처리")
# 해당 프로젝트의 모든 요청은 urls로 온다.