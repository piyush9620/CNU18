from fields import Fields
import copy

class MetaClass(type):
    mockObjects = {}
    primaryIndex = 1
    
    def __init__(self,*args,**kwargs):
        for arg in kwargs:
            attr = object.__getattribute__(self, arg)
            getattr(self,arg).set_value(kwargs[arg])
        
    def save(self):
        self.__class__.mockObjects[self.__class__.primaryIndex] = self.get_clone()
        self.__class__.primaryIndex+=1
    @classmethod
    def all(cls):
        return list(cls.mockObjects.values());
    @classmethod
    def get(cls,id):
        return cls.mockObjects[id]

    def get_clone(self):
        cloned_obj = self.__class__(**self.kw)
        return cloned_obj
    
    @staticmethod
    def get_op_function(self,splits,op):
        if isinstance(splits, (list,)):
            query1 = splits[0]
            query2 = splits[1]
        else:
            query1 = splits
        def opfunc(*args, **kwargs):                
            queryObj1 = args[0]
            if len(args) == 2:
                queryObj2 = args[1]
            returnList = []
            for insObjectKey in self.__class__.mockObjects:
                insObject = self.__class__.mockObjects[insObjectKey]
                if op=="and":
                    if getattr(insObject,query1).get_value() == queryObj1 and getattr(insObject,query2).get_value() == queryObj2:
                        returnList.append(insObject)
                elif op=="or":
                    if getattr(insObject,query1).get_value() == queryObj1 or getattr(insObject,query2).get_value() == queryObj2:
                        returnList.append(insObject)
                elif op=="none":
                    if getattr(insObject,query1).get_value() == queryObj1:
                        returnList.append(insObject)
            return returnList
        return opfunc
        
    @staticmethod
    def get_function(self,name):
        name = name.replace("findBy","")
        orsplits = name.split("Or")
        andsplits = name.split("And")
        if len(orsplits) >1:
            return self.get_op_function(self,orsplits,"or")
        if len(andsplits) >1:
            return self.get_op_function(self,andsplits,"and")
        return self.get_op_function(self,name,"none")
        
    def __getattribute__(self,name):
        if name == "repository":
            return self;
        try:
            attr = object.__getattribute__(self, name)
            return attr
        except Exception as e:
            pass
        if name.startswith("findBy"):
            return self.get_function(self,name)
        else:
            return None  

class ModelBase(metaclass = MetaClass):
    def __init__(self,*args,**kwargs):
        for arg in kwargs:
            attr = object.__getattribute__(self, arg)
            getattr(self,arg).set_value(kwargs[arg])
    
