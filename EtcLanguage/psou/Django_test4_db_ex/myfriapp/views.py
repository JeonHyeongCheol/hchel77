from django.shortcuts import render

from myfriapp.models import Friend

# Create your views here.
def dbex(request):
    return render(request, "friend.html", {"friends":Friend.objects.all()})