from django.contrib import admin
from .models import Category,Restaurant,Review
# Register your models here.

class CategoryAdmin(admin.ModelAdmin):

    list_display = ('name','ID','businesses_count')


class RestaurantAdmin(admin.ModelAdmin):
    list_display = ['ID', 'name', 'neighbourhood', 'address', 'city', 'state', 'postal_code', 'latitude', 'longitude',
                    'stars','_categories', 'review_count', 'is_open', 'is_deleted']

    list_filter = ('city','is_open','categories')
    def delete_queryset(self, request, queryset):
        for item in queryset:
            item.delete()

class ReviewAdmin(admin.ModelAdmin):
    list_display = ('ID','stars', 'feedback', 'pub_date', 'restaurant_id')
    list_filter = ['restaurant']

    def get_readonly_fields(self, request, obj=None):
        if (obj):
            return ['ID', 'restaurant', 'pub_date','stars', 'feedback']
        else:
            return []

    def has_delete_permission(self, request, obj=None):
        return False

admin.site.register(Category,CategoryAdmin);
admin.site.register(Restaurant,RestaurantAdmin);
admin.site.register(Review,ReviewAdmin);