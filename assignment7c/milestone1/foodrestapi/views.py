
from rest_framework_swagger.views import get_swagger_view

from rest_framework import viewsets
from .models import Restaurant,Item
from .serializers import RestaurantSerializer,ItemSerialiser
schema_view = get_swagger_view(title='Pastebin API')
from django_filters import rest_framework as filters

# Create your views here.
class RestaurantViewSet(viewsets.ModelViewSet):
   queryset = Restaurant.objects.all()
   serializer_class = RestaurantSerializer

class ItemViewSet(viewsets.ModelViewSet):
   queryset = Item.objects.all()
   serializer_class = ItemSerialiser