def sumar(a, b):
    """
    Función que suma dos números.
    
    Args:
        a: Primer número
        b: Segundo número
    
    Returns:
        La suma de a y b
    
    Raises:
        ValueError: Si los argumentos no son números
    """
    # Validar que los argumentos sean números
    if not isinstance(a, (int, float)):
        raise ValueError(f"El primer argumento debe ser un número, pero se recibió: {type(a).__name__}")
    
    if not isinstance(b, (int, float)):
        raise ValueError(f"El segundo argumento debe ser un número, pero se recibió: {type(b).__name__}")
    
    return a + b
