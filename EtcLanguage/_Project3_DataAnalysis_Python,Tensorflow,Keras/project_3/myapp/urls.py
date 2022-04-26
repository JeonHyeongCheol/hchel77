from django.urls import path
from . import views
from django.conf.urls.static import static
from django.conf import settings
from django.conf.urls import url


urlpatterns = [
    path('new', views.post_new),
    path('new/view', views.post_view),
    #path('new/ajaxs', views.ajaxs),
    url(r'^like/$', views.post_like, name='post_like'),
]
urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)