from django.urls import path
from my_anal import views

urlpatterns = [
    path('survey', views.SurveyView),
    path('list', views.ListFunc),
]