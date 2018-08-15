from django.db.models.signals import post_save,m2m_changed
from django.dispatch import receiver
from .models import Review,Restaurant
from celery import chord,signature
from .tasks import updateRestaurantStatus,fetchImages
@receiver(post_save, sender=Review)
def stars_handler(sender, instance, **kwargs):
    restaurant = instance.restaurant
    restaurant.review_count += 1
    restaurant.stars = (restaurant.stars * restaurant.review_count + (instance.stars - restaurant.stars)) / restaurant.review_count
    restaurant.save()

@receiver(post_save,sender=Restaurant)
def image_handler(sender, instance, created,**kwargs):
    if not instance.images_added:
        restaurant_id = instance.ID
        #signature("foodservice.tasks.updateRestaurantStatus", args=(99,)).delay()
        fetch_image_task = fetchImages.s(restaurant_id)
        print("task created")
        fetch_image_task.apply_async()



@receiver(m2m_changed, sender=Restaurant.categories.through)
def categories_handler(sender, instance, **kwargs):
    if kwargs['action'] == 'pre_remove':
        diff = -1
    elif kwargs['action'] == 'post_add':
        diff = 1
    else:
        diff = 0
    categories = instance.categories.all()
    for category in categories:
        category.businesses_count += diff
        category.save()


print("I am model")