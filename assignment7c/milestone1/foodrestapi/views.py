
from rest_framework_swagger.views import get_swagger_view
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework import viewsets
from .models import Restaurant,Item
from .serializers import RestaurantSerializer,ItemSerialiser
schema_view = get_swagger_view(title='Pastebin API')

from django_filters import rest_framework as filters

# Create your views here.
class RestaurantFilter(filters.FilterSet):
    cuisines = filters.CharFilter(field_name='cuisines__name')
    name = filters.CharFilter(field_name='name')
    city = filters.CharFilter(field_name='city')

    class Meta:
        model = Restaurant
        fields = ['cuisines','name','city']

class ItemFilter(filters.FilterSet):
    name = filters.CharFilter(field_name='name',lookup_expr="contains")
    min_price = filters.NumberFilter(field_name='price', lookup_expr='gte')
    max_price = filters.NumberFilter(field_name='price', lookup_expr='lte')
    class Meta:
        model = Item
        fields = ['name','min_price','max_price']

class RestaurantViewSet(viewsets.ModelViewSet):
   queryset = Restaurant.objects.all()
   serializer_class = RestaurantSerializer
   filter_backends = (filters.DjangoFilterBackend,)
   filter_class = RestaurantFilter

class ItemViewSet(viewsets.ModelViewSet):
   queryset = Item.objects.all()
   serializer_class = ItemSerialiser
   filter_backends = (filters.DjangoFilterBackend,)
   filter_class = ItemFilter


