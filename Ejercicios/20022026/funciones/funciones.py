# 1. Definición (no se ejecuta aún)
# 'nombre' y 'año_nacimiento' son PARÁMETROS
def calcular_edad(nombre, año_nacimiento):
    # 2. Bloque indentado (Alcance Local)
    edad_actual = 2025 - año_nacimiento # 'edad_actual' es local
    print(f"Calculando para {nombre}...")

    # 3. El valor de retorno (saca el dato)
    return edad_actual

# --- El script principal ---
print("Iniciando programa...")

# 4. Llamada (Call) a la función.
# "Juan" y 1990 son ARGUMENTOS
edad_juan = calcular_edad("Juan", 1990)

print(f"Juan tiene {edad_juan} años.")

edad_ana = calcular_edad("Ana", 2000)
print(f"Ana tiene {edad_ana} años.")
