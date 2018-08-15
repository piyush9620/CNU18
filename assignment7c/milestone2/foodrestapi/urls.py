from . import views
from django.urls import path,include
from django.conf.urls import url

from rest_framework import routers
from rest_framework_swagger.views import get_swagger_view

from . import views

router = routers.DefaultRouter()
router.register(r'restaurants', views.RestaurantViewSet)
router.register(r'items', views.ItemViewSet)


urlpatterns = [
url('swagger/', views.schema_view),
   url(r'', include(router.urls))

]