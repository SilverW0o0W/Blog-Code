class Singleton(type):
    def __init__(cls,name,bases,dic):
        super(Singleton,cls).__init__(name,bases,dic)
        cls.instance = None
    
    def __call__(cls,*args,**kwargs):
        if cls.instance is None:
            print "creating a new instance"
            cls.instance=super(Singleton,cls).__call__(*args,**kwargs)
        else:
            print "warning: only allowed to create one instance, instance already exists!"
        return cls.instance

class MySingleton(object):
    __metaclass__ = Singleton