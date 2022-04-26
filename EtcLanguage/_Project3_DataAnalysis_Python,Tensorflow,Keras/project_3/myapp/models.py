from django.db import models
#from django.utils import timezone

class Post(models.Model):      
    image = models.ImageField(upload_to='%y/%m/%d', blank=True, null=True)







# class Post(models.Model):
#     author = models.ForeignKey('auth.User', on_delete=models.CASCADE)
#     #title = models.CharField(max_length=200)
#     text = models.TextField()
#     image = models.ImageField(upload_to='%Y/%m/%d', null=False)
#     #photo = models.ImageField()
#     tag = models.CharField(max_length=200)
#     created_date = models.DateTimeField(
#             default=timezone.now)
#     published_date = models.DateTimeField(
#             blank=True, null=True)
# 
#     def publish(self):
#         self.published_date = timezone.now()
#         self.save()
# 
# #     def __str__(self):
# #         return self.title
    




# class UploadFileModel(models.Model):
#     title = models.TextField(default='')
#     file = models.FileField(null=True)
    
    
    