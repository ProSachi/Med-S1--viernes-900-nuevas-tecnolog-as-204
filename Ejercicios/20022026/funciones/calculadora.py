from division import division
from restas import restas
from sumas import sumas
from multiplicacion import multiplicaciones
from modulo import modulo
from cuadrados import cudrado

print("___ Iniciando Aplicación")

continuar = True
while(continuar):
    print("1. Sumas")
    print("2. Resta")
    print("3. Multiplicación")
    print("4. División")
    print("5. ¿Son pares?")
    print("6. Cuadrados")
    print("7. Salir")
    
    opcion = int(input("Ingresa el numero de la operación que deseas realizar?"))

    try:
        if(opcion != 7):
            num1= int(input("Ingresa un numero: "))
            num2= int(input("Ingresa otro numero: "))
    except ValueError:
        print("Debes escribir un numero, no en letras")

    match opcion:
        case 1: resultado = sumas(num1, num2)
        case 2: resultado = restas(num1, num2)
        case 3: resultado = multiplicaciones(num1, num2)
        case 4: resultado = division(num1, num2)
        case 5: resultado = modulo(num1, num2)
        case 6: resultado = cudrado(num1,num2)
        case 7: continuar = False 

        
    if(opcion != 7):
        print(f" El resultado de la operación es: {resultado}")



"""     print("Desea continuar realizando operaciones?")
    respuesta = int(input("1 para si  -- 2 para no /n"))
    if(respuesta ==2):
        continuar = False
        print("Finalizando Aplicación")
 """
