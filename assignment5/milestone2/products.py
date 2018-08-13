from base import ModelBase
from fields import Fields
class Product(ModelBase):
    name = Fields.CharField()
    sell_price = Fields.FloatField()
    buy_price = Fields.FloatField()
    quantity = Fields.IntegerField()
    active = Fields.BooleanField()
P = Product(name='PROD-3', sell_price=3.99, buy_price=2.99, quantity=100, active=True)
P.repository.save()
P.all()
