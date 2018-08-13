from base import ModelBase
from fields import Fields
class Product(ModelBase):
    def __init__(self,*args,**kwargs):
        self.name = Fields.CharField()
        self.sell_price = Fields.FloatField()
        self.buy_price = Fields.FloatField()
        self.quantity = Fields.IntegerField()
        self.active = Fields.BooleanField()
        super().__init__(*args,**kwargs)
P = Product(name='PROD-3', sell_price=3.99, buy_price=2.99, quantity=100, active=True)
P.repository.findByname("PROD-3")
P.all()
