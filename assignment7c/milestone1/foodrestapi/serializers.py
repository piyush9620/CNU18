from rest_framework import serializers
from .models import Restaurant ,Item,Cuisines


class CuisineField(serializers.Field):
    def to_representation(self, cuisines):
        return [str(cuisine) for cuisine in cuisines.all()]

    def to_internal_value(self, data):
        result = []
        for cuisine in data:
            print(cuisine)
            try:
                cuisine_obj = Cuisines.objects.get(name=cuisine)
            except Cuisines.DoesNotExist as e:
                cuisine_obj = Cuisines(name=cuisine)
                cuisine_obj.save()
            result.append(cuisine_obj.id)
        return result

class RestaurantSerializer(serializers.ModelSerializer):
    cuisines = CuisineField()
    class Meta:
        model = Restaurant
        fields = ('id', 'name', 'city', 'latitude', 'longitude', 'rating','is_open','cuisines')

class ItemSerialiser(serializers.ModelSerializer):
    restaurant = RestaurantSerializer(read_only=True)
    restaurant_id = serializers.PrimaryKeyRelatedField(
        queryset=Restaurant.objects.all(),
        source='restaurant',
        write_only=True
    )

    class Meta:
        model = Item
        fields = ('id', 'name','price','restaurant_id','restaurant')