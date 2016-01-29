__author__ = 'oventura'
import random
from PIL import Image
babies = 1
buenas = 0
malas = 0
while babies < 100:
    numero1 = random.randrange(1, 10)
    numero2 = random.randrange(1, 10)
    print '###############################################\n'
    print 'Llevas', buenas ,  'bien y  ', malas ,' mal\n'
    print '###############################################\n'
    print '\n'
    print 'Cuanto es', numero1 ,  '+ ', numero2
    name = raw_input('Introuduce el resultado?\n')
    if int(name) == int(numero1) + int(numero2):
        print 'Fenomenal !!!!!!!!!'
        image = Image.open('/Users/oventura/Desktop/imagenes/i' + str(numero1) + '.jpeg')
        image.show()
        buenas = int(buenas) + 1
    else:
        print 'Sigue Intentandolo era', numero1 + numero2
        malas =int(malas) +1
        image2 = Image.open('/Users/oventura/Desktop/imagenes/mal.jpe12g')
        image2.show()
