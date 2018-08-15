from django.db import models
from django.core.validators import MinValueValidator,MaxValueValidator

# Create your models here.


class Cuisines(models.Model):
    name = models.CharField(max_length=200,unique=True)

    class Meta:
        unique_together = ('name',)
        ordering = ['name']

    def __str__(self):
        return self.name

    def __unicode__(self):
        return self.name


class Restaurant(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    city = models.CharField(max_length=200)
    latitude = models.FloatField(validators=[MinValueValidator(-90),MaxValueValidator(90)])
    longitude = models.FloatField(validators=[MinValueValidator(-180),MaxValueValidator(180)])
    rating = models.FloatField(validators=[MinValueValidator(0)])
    is_open = models.BooleanField()
    cuisines = models.ManyToManyField(Cuisines, related_name='restaurants')
    is_deleted = models.BooleanField(default=False)


    def __str__(self):
        return self.name

    def delete(self, using=None, keep_parents=False):
        self.is_deleted = True
        self.save()



class Item(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    price = models.PositiveIntegerField()
    restaurant = models.ForeignKey(Restaurant,on_delete=models.SET_NULL,null=True)

    def __str__(self):
        return self.name