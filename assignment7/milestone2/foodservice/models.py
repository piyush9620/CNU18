from django.db import models
from django.db.models.signals import post_save ,m2m_changed ,post_delete,pre_delete
from django.dispatch import receiver
from django.core.validators import MaxValueValidator, MinValueValidator
from django.db.models import Avg

# Create your models here.

class Category(models.Model):
    class Meta:
        verbose_name_plural = "categories"
    name = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published', auto_now=True)
    ID = models.AutoField(primary_key=True)
    businesses_count = models.PositiveIntegerField(default=0,editable=False)

    def _businesses_count(self):
        return self.restaurant_set.filter(is_deleted=False).count()

    businesses_count.short_description = "# of businesses with this category"
    def __str__(self):
        return str(self.name)


class Restaurant(models.Model):
    name = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published', auto_now=True)
    ID = models.AutoField(primary_key=True)
    neighbourhood = models.CharField(max_length=200)
    address = models.CharField(max_length=200)
    city = models.CharField(max_length=200)
    state = models.CharField(max_length=200)
    postal_code = models.IntegerField(default=0)
    latitude = models.FloatField(default=0.0)
    longitude = models.FloatField(default=0.0)
    stars = models.FloatField(default=0.0,editable=False,validators=[MaxValueValidator(10), MinValueValidator(0)])
    is_open = models.BooleanField(default=False)
    review_count = models.PositiveIntegerField(default=0,editable=False)
    is_deleted = models.BooleanField(default=False,editable=False)
    categories = models.ManyToManyField(Category)

    def _categories(self):
        return "\n".join([cat.name for cat in self.categories.all()])

    def _stars(self):
        return self.review_set.all().aggregate(Avg('stars'))['stars__avg']


    def _review_count(self):
        return self.review_set.all().count();

    def delete(self, using=None, keep_parents=False):
        self.is_deleted = True
        self.save()

    def __str__(self):
        return str(self.name)


class Review(models.Model):
    ID = models.AutoField(primary_key=True)
    pub_date = models.DateTimeField('date published', auto_now=True)
    stars = models.PositiveIntegerField(default=0)
    feedback = models.CharField(max_length=500,default="")
    restaurant = models.ForeignKey('Restaurant',on_delete=models.CASCADE)

@receiver(post_save, sender=Review)
def stars_handler(sender, instance, **kwargs):
    restaurant = instance.restaurant
    restaurant.review_count = restaurant._review_count()
    #restaurant.stars = (restaurant.stars * restaurant.review_count + (instance.stars - restaurant.stars)) / restaurant.review_count
    restaurant.stars = restaurant._stars();
    print(restaurant.stars)
    restaurant.save()

@receiver(pre_delete, sender=Review)
def stars_handler_delete(sender, instance, **kwargs):
    restaurant = instance.restaurant
    restaurant.review_count = restaurant._review_count()
    # restaurant.stars = (restaurant.stars * restaurant.review_count + (instance.stars - restaurant.stars)) / restaurant.review_count
    restaurant.stars = restaurant._stars();
    restaurant.save()

@receiver(m2m_changed, sender=Restaurant.categories.through)
def categories_handler(sender, instance, **kwargs):
    categories = instance.categories.all()
    for category in categories:
        category.businesses_count = category._businesses_count()
        category.save()

@receiver(post_delete, sender=Restaurant)
def categories_handler_delete(sender, instance, **kwargs):
    categories = instance.categories.all()
    for category in categories:
        category.businesses_count = category._businesses_count()
        category.save()
