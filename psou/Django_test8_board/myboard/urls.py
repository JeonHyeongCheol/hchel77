from django.urls import path

from myboard import views


urlpatterns = [
    path('list', views.ListFunc),
]