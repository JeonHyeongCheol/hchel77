from django.db import models

# Create your models here.
class Maker(models.Model):
    mname = models.CharField(max_length=10)
    tel = models.CharField(max_length=30)
    addr = models.CharField(max_length=50)
    
    class Meta:
        ordering = ('-id',)
        
    def __str__(self): # 관리자 페이지에서 foreign key 연결시 object로 나옴. 있었던 내용들을 보여줄려면 이렇게 써주면 foreign key 리스트를 보여줌.
        return self.mname # 해당 칼럼명 써주면 됨.
        
class Product(models.Model):
    pname = models.CharField(max_length=10)
    price = models.IntegerField()
    maker_name = models.ForeignKey(Maker, on_delete = models.CASCADE) # ForeignKey : 자동으로 PK 칼럼 연결, on_delete 하면 Maker가 지워지면 같이 지워짐.
    # maker_name는 네임명이 들어가는 것이 아니라 번호가 들어감(알아서 Auto)