from base import ModelBase,MetaClass
from fields import Fields
class Product(metaclass = MetaClass):
    def __init__(self,*args,**kwargs):
        self.name = Fields.CharField()
        self.sell_price = Fields.FloatField()
        self.buy_price = Fields.FloatField()
        self.quantity = Fields.IntegerField()
        self.active = Fields.BooleanField()
        for arg in kwargs:
            attr = object.__getattribute__(self, arg)
            getattr(self,arg).set_value(kwargs[arg])

P = Product(name='PROD-3', sell_price=3.99, buy_price=2.99, quantity=100, active=True)
Product.findByname("PROD-3")
Product.all()
