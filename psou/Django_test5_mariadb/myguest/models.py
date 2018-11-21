from django.db import models

# Create your models here.
class Guest(models.Model):
    myno = models.PositiveIntegerField(auto_created=True, primary_key = True)
    title = models.CharField(max_length=30)
    content = models.TextField()
    regdate = models.DateTimeField()