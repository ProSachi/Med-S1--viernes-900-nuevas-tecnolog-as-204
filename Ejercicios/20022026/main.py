from operaciones import sumar


def main():
    """Función principal del programa."""
    # Ejemplo 1: Uso correcto de la función sumar
    try:
        numero1 = 15
        numero2 = 27
        resultado = sumar(numero1, numero2)
        print(f"✓ La suma de {numero1} + {numero2} = {resultado}")
    except ValueError as e:
        print(f"✗ Error: {e}")
    
    # Ejemplo 2: Con números decimales
    try:
        x = 10.5
        y = 5.3
        print(f"✓ La suma de {x} + {y} = {sumar(x, y)}")
    except ValueError as e:
        print(f"✗ Error: {e}")
    
    
if __name__ == "__main__":
    main()
