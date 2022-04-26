from django.contrib import admin
from myfriapp.models import Friend

# Register your models here.
class FriendAdmin(admin.ModelAdmin):
    list_display = ('id', 'irum', 'juso', 'nai')
    
admin.site.register(Friend, FriendAdmin)