def modulo (a, b):
    if(a % 2 == 0 and b % 2 == 0):
        return "Ambos numeros son pares"
    elif(a % 2 != 0 and b % 2 != 0):
        return "Ambos numeros son impares"
    elif(a% 2 == 0 and b % 2 != 0 ):
        return "El primer numero es par y el segundo numero es impar"
    else: return "El primer numero es impar y el segundo numero es par"
