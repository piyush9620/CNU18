from django.contrib import admin
from .models import Category,Restaurant
# Register your models here.

class CategoryAdmin(admin.ModelAdmin):

    list_display = ('Name','ID','businesses_count')


class RestaurantAdmin(admin.ModelAdmin):
    list_display = ['ID', 'Name', 'Neighbourhood', 'Address', 'City', 'State', 'Postal_Code', 'Latitude', 'Longitude',
                    'Stars','_categories', 'review_count', 'Is_Open', 'Is_Deleted']


    def delete_queryset(self, request, queryset):
        for item in queryset:
            item.delete()

admin.site.register(Category,CategoryAdmin);
admin.site.register(Restaurant,RestaurantAdmin);