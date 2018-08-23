class Shape(object):
    def __init__(self):
        pass
    def draw(self):
        pass

class Triangle(Shape):
    def __init__(self):
        print "I am a triangle"
    
    def draw(self):
        pass

class Rectangle(Shape):
    def __init__(self):
        print "I am a rectnagle"
    def draw(self):
        print "I am drawing triangle"

class Trapezoid(Shape):
    def __init__(self):
        print "I am a trapezoid"
    def draw(self):
        print "I am drawing triangle"

class Diamond(Shape):
    def __init__(self):
        print "I am a diamond"
    def draw(self):
        print "I am drawing triangle"

class ShapeFactory(object):
    shapes={
        'triangle':Triangle,
        'rectangle':Rectangle,
        'trapezoid':Trapezoid,
        'diamond':Diamond
        }
    
    def __new__(cls,name):
        if name in ShapeFactory.shapes.keys():
            print "creating a new shape %s" % name
            return ShapeFactory.shapes[name]()
        else:
            print "creating a new shape %s" % name
            return Shape