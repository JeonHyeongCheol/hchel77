"""Django_test2 URL Configuration

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
from django.urls.conf import include

from gtapp import views
from gtapp.views import MyCallView


urlpatterns = [
    path('admin/', admin.site.urls),
    
    path('gtapp/', views.MainFunc, name='MainFunc'), # Function views! # 'gtapp'은 요청명을 뜻함. 공백을 만들면 url에서 공백을 줘야 함.
    path('gtapp/callget', MyCallView.as_view()),
    path('gtapp/', include('gtapp.urls')), # Including another URLconf
    # gtapp/ 만 요청하면 MainFunc으로 gtapp/요청명 을 요청하면 gtapp.urls로 감.
]
