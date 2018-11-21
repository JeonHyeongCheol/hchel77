"""Django_test3 URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from sessionapp import views


urlpatterns = [
    path('admin/', admin.site.urls),
    # 어플리케이션이 많을 때는 여기에 다 쓰지 말고 앱에 위임해서 따로 url을 관리 해야 함.
    path('', views.main),
    path('setos/', views.setosFunc),
    path('showos/', views.showos),
]
