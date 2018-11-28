from django.shortcuts import render

# Create your views here.
def Main(request):
    return render(request, "main.html")

def SurveyView(request):
    return render(request, "survey.html")

def ListFunc(request):
    return render(request, "list.html")