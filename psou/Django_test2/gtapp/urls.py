'''
Created on 2018. 11. 21.

gtapp.urls
'''
from django.urls import path

from gtapp import views


urlpatterns = [
    path('insert', views.InsertFunc, name = 'InsertFunc'),
]
