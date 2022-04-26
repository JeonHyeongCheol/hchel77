from django.db import models

# Create your models here.
class Guest(models.Model):
    myno = models.PositiveIntegerField(auto_created=True, primary_key = True)
    title = models.CharField(max_length=30)
    content = models.TextField()
    regdate = models.DateTimeField()
    
    # 정렬 할 수 있는 방법 2
    class Meta:
        ordering = ('title',) # ordering 타입은 튜플타입으로 써줘야 함.
        #ordering = ('-myno')