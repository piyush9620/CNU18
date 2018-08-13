from django.db import models

# Create your models here.

class Category(models.Model):
    class Meta:
        verbose_name_plural = "categories"
    Name = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published', auto_now=True)
    ID = models.AutoField(primary_key=True)
    Is_Deleted = models.BooleanField(default=False,editable=False)


    def businesses_count(self):
        return self.restaurant_set.count()

    def delete(self, using=None, keep_parents=False):
        self.Is_Deleted = True
        self.save()

    businesses_count.short_description = "# of businesses with this category"
    def __str__(self):
        return str(self.Name)


class Restaurant(models.Model):
    Name = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published', auto_now=True)
    ID = models.AutoField(primary_key=True)
    Neighbourhood = models.CharField(max_length=200)
    Address = models.CharField(max_length=200)
    City = models.CharField(max_length=200)
    State = models.CharField(max_length=200)
    Postal_Code = models.IntegerField(default=0)
    Latitude = models.FloatField(default=0.0)
    Longitude = models.FloatField(default=0.0)
    Stars = models.FloatField(default=0.0,editable=False)
    Is_Open = models.BooleanField(default=False)
    review_count = models.IntegerField(default=0,editable=False)
    Is_Deleted = models.BooleanField(default=False,editable=False)
    categories = models.ManyToManyField(Category)

    def _categories(self):
        return "\n".join([cat.Name for cat in self.categories.filter()])

    def getReviewCount(self):
        pass

    def delete(self, using=None, keep_parents=False):
        self.Is_Deleted = True
        self.save()

    def __str__(self):
        return str(self.Name)

